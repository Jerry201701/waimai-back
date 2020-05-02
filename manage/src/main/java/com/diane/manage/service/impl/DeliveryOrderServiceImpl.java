package com.diane.manage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.diane.manage.dao.*;
import com.diane.manage.model.*;
import com.diane.manage.service.DeliveryOrderService;
import com.diane.manage.util.PayUtil;
import com.diane.manage.vo.ManagerQueryVo;
import com.diane.manage.vo.OrderInfoVo;
import com.diane.manage.vo.delivery.StatisticsDeliveryVo;
import com.diane.manage.vo.order.*;
import com.diane.manage.vo.shop.ShopDispatchVo;
import com.diane.manage.vo.shop.ShopStatisticsRespVo;
import com.diane.manage.vo.shop.ShopStatisticsVo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class DeliveryOrderServiceImpl implements DeliveryOrderService {
    private static  final String PAY_SUCCESS="PAY_SUC";
    private static final  String REFUND_SUCCESS="REFUND";
    @Autowired
    private DeliveryOrderMapper deliveryOrderMapper;
    @Autowired
    private OrderGoodMapper orderGoodMapper;
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private SettlingModelMapper settlingModelMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private RevenueStatisticsMapper revenueStatisticsMapper;
    @Autowired
    private GoodMapper goodMapper;


    @Override
    public int createDeliveryOrder(DeliveryOrderInfo deliveryOrderInfo) {

       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //String dateTime = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        deliveryOrderInfo.setOrderTime(timestamp);
        int i=deliveryOrderMapper.insertSelective(deliveryOrderInfo);
        List<OrderGood> goods=deliveryOrderInfo.getGood();
        for (OrderGood good:goods){
            good.setOrderId(deliveryOrderInfo.getId());
            good.setCreateTime(new Timestamp(System.currentTimeMillis()));
            goodMapper.updateStock(good.getQuantity(),good.getGoodId());
            orderGoodMapper.insertSelective(good);
        }
        return i;
    }

    @Override
    public List<OrderInfoVo> deliveryOrderList(OrderQueryInfo orderQueryInfo) {
        List<OrderInfoVo> orders= deliveryOrderMapper.deliveryOrderList(orderQueryInfo);
        /*
        Iterator<OrderInfoVo> iterator=orders.iterator();
        while (iterator.hasNext()) {
            orderGoodMapper.listGoodByOrder(iterator.next().getOrderId());
        }
        */
        for (OrderInfoVo order :orders){
            order.setGood(orderGoodMapper.listGoodByOrder(order.getOrderId()));

        }
        return orders;
    }


    @Override
    public int changeOrderStatus(DeliveryShop deliveryShop) {
        /*
        如果商家确认订单要做3件事：1，给商家记账，2，给配送员记账 3，改变订单状态
         */
        if (deliveryShop.getHandleStatus()==5){

            ThreadPoolExecutor executor=new ThreadPoolExecutor(2,3,1,
                    TimeUnit.SECONDS,new LinkedBlockingDeque<>());
            CountDownLatch latch=new CountDownLatch(2);
            executor.execute(()->{
                try {
                    Wallet wallet=new Wallet();
                    /*
                    骑手的收入就是配送费
                     */
                    wallet.setBalance(deliveryShop.getDeliveryFee());
                    wallet.setDeliveryId(deliveryShop.getDeliveryId());
                    wallet.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
                    walletMapper.recordDeliveryBalance(wallet);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });

            executor.execute(()->{
                try {
                    Wallet wallet=new Wallet();
                    RevenueStatistics revenueStatistics=new RevenueStatistics();
                    revenueStatistics.setCompanyId(deliveryShop.getCompanyId());
                    revenueStatistics.setShopId(deliveryShop.getShopId());
                    revenueStatistics.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    revenueStatistics.setDeliveryReward(deliveryShop.getDeliveryFee());
                    revenueStatistics.setOrderNumber(deliveryShop.getOrderNumber());
                    /*
                    商家收入计算规则：订单实际支付金额-配送费-平台提成-手续费
                    平台提成是在手续费后
                     */
                    SettlingModel settlingModel=shopMapper.findRuleModelByShop(deliveryShop.getShopId());
                    BigDecimal amount=new BigDecimal(deliveryShop.getAmount());
                    BigDecimal fee=amount.multiply(new BigDecimal(settlingModel.getFee()).divide(new BigDecimal(100)));
                    amount=amount.subtract(fee);
                    revenueStatistics.setTotalIncome(amount.intValue());
                    revenueStatistics.setPlatFormReward(0);

                    if ((amount).compareTo(new BigDecimal(100))==1){
                        BigDecimal reward=amount.multiply(new BigDecimal(settlingModel.getPercentage()).divide(new BigDecimal(100)));
                        revenueStatistics.setPlatFormReward(reward.intValue());
                        amount=amount.subtract(reward);

                    }
                    revenueStatistics.setShopReward(amount.intValue());
                    wallet.setBalance(amount.intValue());
                    wallet.setCompanyId(deliveryShop.getCompanyId());
                    wallet.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
                    walletMapper.recordCompanyBalance(wallet);
                    revenueStatisticsMapper.addRevenueStatistics(revenueStatistics);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });


            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

          //  return deliveryOrderMapper.changeOrderStatus(deliveryShop);

            //shopMapper.changeOrderIncome(deliveryShop.getPayAmount(),deliveryShop.getShopId());
        }

        return deliveryOrderMapper.changeOrderStatus(deliveryShop);
    }

    @Override
    public Integer showGoodLabelPrice(OrderGood orderGood) {

        return deliveryOrderMapper.showGoodLabelPrice(orderGood);
    }

    @Override
    public Integer showGoodPrice(OrderGood orderGood) {
        return deliveryOrderMapper.showGoodPrice(orderGood);
    }
    /*
    返回订单支付成功的信息
     */
    @Override
    public OrderInfoVo getOrderDetail(Long orderId) {
        OrderInfoVo orderInfoVo=deliveryOrderMapper.deliveryOrderDetail(orderId);
        orderInfoVo.setGood(orderGoodMapper.listGoodByOrder(orderId));
        if (orderInfoVo.getPayStatus()==2){
        return orderInfoVo;
        }

      PayResultDetail payResultDetail= JSONObject.parseObject(PayUtil.getOrderPayStatus(orderInfoVo.getOrderNumber()).getString("body"), PayResultDetail.class);
        if (payResultDetail.getOrderStatus().equals(PAY_SUCCESS)){
            orderInfoVo.setPayStatus(2);
        }
        if (payResultDetail.getOrderStatus().equals(REFUND_SUCCESS)){
            orderInfoVo.setPayStatus(11);
        }
        orderInfoVo.setPayStatus(1);
        return orderInfoVo;
    }

    @Override
    public Page<ShopOrderRespVo> listOrderByShop(OrderQueryInfo queryInfo) {
        return deliveryOrderMapper.listOrderByShop(queryInfo);
    }

    @Override
    public OrderInfoVo deliveryOrderDetailByNumber(String orderNumber) {
        OrderInfoVo orderInfoVo=deliveryOrderMapper.deliveryOrderDetailByNumber(orderNumber);
        orderInfoVo.setGood(orderGoodMapper.listGoodByOrder(orderInfoVo.getOrderId()));

        if (orderInfoVo.getPayStatus()==1){
            PayResultDetail payResultDetail= JSONObject.parseObject(PayUtil.getOrderPayStatus(orderInfoVo.getOrderNumber()).getString("body"), PayResultDetail.class);
            if (payResultDetail.getOrderStatus().equals(PAY_SUCCESS)){
                orderInfoVo.setPayStatus(2);
            }
        if (payResultDetail.getOrderStatus().equals(REFUND_SUCCESS)){
            orderInfoVo.setPayStatus(11);
        }
        }
        return orderInfoVo;
    }

    @Override
    public DeliveryShop getPayAmountByOrderNumber(String orderNumber) {
        return deliveryOrderMapper.getPayAmountByOrderNumber(orderNumber);
    }

    @Override
    public int refundManagement(String orderNumber, String reason, Integer refundAmount) {
        String refundNumber=orderNumber.concat("_refund");
         BigDecimal refund=new BigDecimal(refundAmount);
         refund=refund.divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
        if (refundToCustomer(orderNumber,reason,refundNumber,refund)==1){
            DeliveryShop deliveryShop=new DeliveryShop();
            deliveryShop.setOrderNumber(orderNumber);
            deliveryShop.setHandleStatus(3);
            deliveryShop.setRefundAmount(refundAmount);
            return  deliveryOrderMapper.changeOrderStatus(deliveryShop);
        }
        return 0;
    }

    private int  refundToCustomer(String orderNumber, String reason, String refundNumber,BigDecimal refundAmount) {
        // RefundResultVo refundResultVo =new RefundResultVo();
        //  int i=deliveryOrderMapper.changeOrderStatus(orderNumber,status);
        // DeliveryOrderStatusVo deliveryOrderStatusVo=deliveryOrderMapper.getPayAmountByOrderNumber(orderNumber);
        //到支付中心查询订单，订单支付成功，就申请退款并且退实际收款金额
        PayResultDetail payResultDetail = JSONObject.parseObject(PayUtil.getOrderPayStatus(orderNumber).getString("body"), PayResultDetail.class);
        if (payResultDetail.getOrderStatus().equals("PAY_SUC")){
            //支付平台查询的实际收款金额要大于或者等于要求退款的金额
            if (payResultDetail.getReceiveAmount().compareTo(refundAmount)==1||payResultDetail.getReceiveAmount().compareTo(refundAmount)==0){
                JSONObject result=PayUtil.refundToCustomer(orderNumber,refundAmount,reason,refundNumber);
                if (result.getString("result").equals("200")){
                    return 1;
                }
            }
            //查询退款订单
          //  JSONObject queryResult=PayUtil.queryRefundOrder(orderNumber,refundNumber);
           // System.out.println(queryResult);
            return 2;
        }

        //  BigDecimal amount= new BigDecimal(deliveryOrderStatusVo.getAmount()).divide(new BigDecimal(100));
       /*
        if (payResultDetail.getOrderStatus().equals(PAY_SUCCESS)) {
             JSONObject result=PayUtil.refundToCustomer(orderNumber,amount.setScale(2,BigDecimal.ROUND_HALF_UP),reason);
             JSONObject result = PayUtil.refundToCustomer(orderNumber, payResultDetail.getReceiveAmount(), reason);
            Integer code=Integer.valueOf(PayUtil.refundToCustomer(orderNumber, payResultDetail.getReceiveAmount(), reason,refundNumber).getString("result"));
              refundResultVo = JSONObject.parseObject(result.getString("body"), RefundResultVo.class);
             System.out.println(refundResultVo.getRefundStatus());
            return code;

        }
        */
        return 1;
    }

    @Override
    public int customerCancelOrder(String orderNumber, String reason, BigDecimal refundAmount) {
        JSONObject result=PayUtil.refundToCustomer(orderNumber,refundAmount,reason,orderNumber.concat("_refund"));
        if (result.getString("result").equals("200")){
            return 1;
        }
        return 0;
    }



    @Override
    public int remindOrder(String orderNumber) {
        return deliveryOrderMapper.remindOrder(orderNumber);
    }

    @Override
    public List<StatisticsDeliveryVo> deliveryStatistics(Long deliveryId, String deliveryTime) {
        List<StatisticsDeliveryVo> list= deliveryOrderMapper.deliveryStatistics(deliveryId,deliveryTime);
//        for (StatisticsDeliveryVo statisticsDeliveryVo:list){
//            Integer successNumber=deliveryOrderMapper.findSuccessNumber(deliveryId,statisticsDeliveryVo.getDeliveryDate());
//            statisticsDeliveryVo.setSuccessCount(successNumber);
//            statisticsDeliveryVo.setFailCount(statisticsDeliveryVo.getTotalDeliveryNumber()-successNumber);
//        }
        return list;
    }

    @Override
    public int dispatchDelivery(ShopDispatchVo shopDispatchVo) {
        return deliveryOrderMapper.dispatchDelivery(shopDispatchVo);
    }

    @Override
    public ShopStatisticsRespVo shopStatistics(ShopStatisticsVo shopStatisticsVo) {
        return deliveryOrderMapper.shopStatistics(shopStatisticsVo);
    }

    @Override
    public Integer findUnPayAmount(String orderNumber) {
        return deliveryOrderMapper.findUnPayAmount(orderNumber);
    }

    @Override
    public Page<ShopStatisticsRespVo> managerShopStatistics(ManagerQueryVo managerQueryVo) {
        return deliveryOrderMapper.managerShopStatistics(managerQueryVo);
    }
}
