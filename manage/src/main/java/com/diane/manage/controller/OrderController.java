package com.diane.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.diane.common.http.HttpResult;
import com.diane.manage.model.*;
import com.diane.manage.service.*;
import com.diane.manage.util.CommonUtil;
import com.diane.manage.util.PayUtil;
import com.diane.manage.vo.AddressQuery;
import com.diane.manage.vo.CallbackVo;
import com.diane.manage.vo.comment.CommentPageResultVo;
import com.diane.manage.vo.OrderInfoVo;
import com.diane.manage.vo.customer.CustomerOrderVo;
import com.diane.manage.vo.delivery.DeliveryRuleVo;
import com.diane.manage.vo.order.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;


@RestController
public class OrderController {

    private static final String NOTIFY_URL="https://xxxxxxxxxxxxxxxx/customer/notify";


    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private DeliveryOrderService deliveryOrderService;
    @Autowired
    private DeliveryCommentService deliveryCommentService;
    @Autowired
    private AddressInfoService addressInfoService;
    @Autowired
    private SettlingModelService settlingModelService;
    @Autowired
    private FullReductionService fullReductionService;
    @Autowired
    private ShopService shopService;
    @PostMapping(value = "/customer/create/order")
    public HttpResult createOrder(@RequestBody DeliveryOrderInfo deliveryOrderInfo){
        AddressQuery addressQuery=new AddressQuery();
        addressQuery.setAddress(deliveryOrderInfo.getAddress());
        addressQuery.setContact(deliveryOrderInfo.getContact());
        addressQuery.setPhone(deliveryOrderInfo.getPhone());
        addressInfoService.saveAddressInfo(addressQuery);
        deliveryOrderInfo.setAddressId(addressQuery.getAddressId());
        // String openid="oeK-B4mVTf-cJJxUfes_5ccqJ6YA";
        // Integer amount=1;
        List<OrderGood> goods=deliveryOrderInfo.getGood();
        Integer amount=0;
        for (OrderGood good:goods){
            Integer price=0;
            if (good.getLabelId()==null){
                price=deliveryOrderService.showGoodPrice(good);
            }else {
             price=deliveryOrderService.showGoodLabelPrice(good);
            }
            amount=amount+price*good.getQuantity();
        }

        deliveryOrderInfo.setAmount(amount);
        deliveryOrderInfo.setPayStatus(1);
        deliveryOrderInfo.setOrderNumber(CommonUtil.getUUID());
        Integer packageFee= shopService.findPackageFee(deliveryOrderInfo.getShopId());
        if (packageFee!=null){
        amount=amount+packageFee;
        }
        Integer deliveryFee=0;
        deliveryOrderInfo.setDeliveryFee(deliveryFee);
        if (deliveryOrderInfo.getDelivery()){
        deliveryFee=settlingModelService.computeDeliveryFee(deliveryOrderInfo.getShopId(),amount);
        deliveryOrderInfo.setDeliveryFee(deliveryFee);
        }
        FullReduction fullReduction=fullReductionService.findSingleFullReductionByShop(deliveryOrderInfo.getShopId(),amount);
        if (fullReduction!=null&&amount>=fullReduction.getLimitPrice()){
            amount=amount-fullReduction.getPrice();
        }
        deliveryOrderInfo.setPayAmount(amount+deliveryFee);
        deliveryOrderService.createDeliveryOrder(deliveryOrderInfo);
        BigDecimal payAmount=BigDecimal.valueOf(amount+deliveryFee);
        payAmount=payAmount.divide(BigDecimal.valueOf(100));
        payAmount=payAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
        String subject ="diane wai mai";
        JSONObject jsonObject=PayUtil.payOrderAmount(deliveryOrderInfo.getCommand(),
                deliveryOrderInfo.getSubAppId(),
                deliveryOrderInfo.getOpenid(),
                payAmount,
                subject,
                deliveryOrderInfo.getOrderNumber(),
                NOTIFY_URL,
                deliveryOrderInfo.getPaysucUrl(),
                deliveryOrderInfo.getPayfailUrl());
        return HttpResult.ok(jsonObject);

    }


    @PostMapping(value = "/customer/notify")
    public OrderPayStatusVo notifyPayResult(@RequestBody NotifyVo notifyVo){

        System.out.println("回调成功");
       // amqpTemplate.convertAndSend("mai",notifyVo.getDwkey().getLocal_order_no());
        amqpTemplate.convertAndSend("mai",new CallbackVo(notifyVo.getDwkey().getLocal_order_no(),notifyVo.getDwkey().getStatus()));

      /*
        if (notifyVo.getDwkey().getStatus().equals("PAY_SUC")){
        amqpTemplate.convertAndSend("dian",notifyVo.getDwkey().getLocal_order_no());
        }
        if (notifyVo.getDwkey().getStatus().equals("REFUND")){
              deliveryOrderService.changeOrderStatus(notifyVo.getDwkey().getLocal_order_no(),11);
        }

       */

        return new OrderPayStatusVo("200","交易成功");
    }

    @PostMapping(value = "/customer/order/list")
    public HttpResult listOrderInfo(@RequestBody OrderQueryInfo queryInfo){
        if (queryInfo.getOpenid()==null){
            return HttpResult.error("不明用户");
        }

        List<OrderInfoVo> list = deliveryOrderService.deliveryOrderList(queryInfo);
        PageHelper.startPage(queryInfo.getPageNum(),queryInfo.getPageSize());

        PageInfo pageInfo = new PageInfo(list);
        if (pageInfo.getTotal()==0){
            return HttpResult.ok(pageInfo.getList());
        }
        return HttpResult.ok(pageInfo);
    }
    /*
    单笔订单查询
    如果支付中心回调失败，需要手动查询订单支付是否成功
     */
    @GetMapping(value = "/customer/order/detail")
    public HttpResult showOrderDetail(@RequestParam(value = "openid")String openid,
                                      @RequestParam(value = "id") Long id){
        if (openid==null||openid.isEmpty()){
            return HttpResult.error("不明身份");
        }

        return HttpResult.ok(deliveryOrderService.getOrderDetail(id));
    }

    /*
   用户取消订单和催单
    */
    @PostMapping(value = "/customer/order/edit")
    public HttpResult customerOrderEdit(@RequestBody CustomerOrderVo customerOrderVo){
        DeliveryShop deliveryShop =deliveryOrderService.getPayAmountByOrderNumber(customerOrderVo.getOrderNumber());


        if (customerOrderVo.getType()==1){
            if (deliveryShop.getHandleStatus()!=null&&deliveryShop.getHandleStatus()!=1){
                return HttpResult.error("商家已接单，不能取消");
            }

            PayResultDetail payResultDetail = JSONObject.parseObject(PayUtil.getOrderPayStatus(customerOrderVo.getOrderNumber()).getString("body"), PayResultDetail.class);

            if (payResultDetail.getOrderStatus().equals("PAY_SUC")){
               int i= deliveryOrderService.customerCancelOrder(customerOrderVo.getOrderNumber(),"用户取消订单",payResultDetail.getReceiveAmount());
                if (i==1){
                    deliveryShop.setPayStatus(3);
                    deliveryOrderService.changeOrderStatus(deliveryShop);
                    return HttpResult.ok("用户取消订单成功");
                }
                deliveryShop.setPayStatus(4);
                deliveryOrderService.changeOrderStatus(deliveryShop);
                return HttpResult.error("用户取消订单失败");
            }

            deliveryShop.setPayStatus(9);
            deliveryOrderService.changeOrderStatus(deliveryShop);
            return HttpResult.ok("用户取消订单成功");

        }
        if (customerOrderVo.getType()==2){
            System.out.println("用户催单");
            return HttpResult.ok(deliveryOrderService.remindOrder(customerOrderVo.getOrderNumber()));

        }

        return HttpResult.error("编辑订单失败");
    }

    /*
    @PostMapping(value = "/customer/shop/order/edit")
    public HttpResult customerEditOrder(@RequestBody OrderManagementVo orderManagementVo){
        if (orderManagementVo.getType()==1){
            DeliveryShop deliveryShop =deliveryOrderService.getPayAmountByOrderNumber(orderManagementVo.getOrderNumber());
            if (deliveryShop.getPayStatus()==3||deliveryShop.getPayStatus()==4){
                return HttpResult.error("商户已经接单不能取消订单");
            }
           /*
            if (editOrderService.customerCancelOrder(orderManagementVo.getOrderNumber())==1){
                return HttpResult.ok("订单取消成功");
            }
            if (editOrderService.customerCancelOrder(orderManagementVo.getOrderNumber())==2){
                return HttpResult.error("订单取消失败");
            }
             if (editOrderService.customerCancelOrder(orderManagementVo.getOrderNumber())==3){
                return HttpResult.ok("请耐心等待，正在退款中");
            }
        }
        if (orderManagementVo.getType()==2){
            System.out.println("用户催单");
        }
        if (orderManagementVo.getType()==3){
        }
        return HttpResult.error("订单编辑失败");
    }
    */

    @PostMapping(value = "/customer/shop/comment/add")
    public HttpResult addDeliveryComment(@RequestBody DeliveryComment deliveryComment){

            int total =deliveryComment.getAttitude()+deliveryComment.getSpeed();
            BigDecimal grade =new BigDecimal(total).divide(new BigDecimal(2));
            BigDecimal maxComment=new BigDecimal(3.5);
            BigDecimal  minComment=new BigDecimal(2);
            deliveryComment.setMiddle(true);
            deliveryComment.setNegative(false);
            deliveryComment.setPraise(false);

            if (grade.compareTo(maxComment)==1){
                deliveryComment.setPraise(true);
                deliveryComment.setMiddle(false);
                deliveryComment.setNegative(false);

            }
            if (grade.compareTo(minComment)==-1){
                deliveryComment.setNegative(true);
                deliveryComment.setPraise(false);
                deliveryComment.setMiddle(false);

            }
            return HttpResult.ok(deliveryCommentService.saveComment(deliveryComment));
    }

    @GetMapping(value="/customer/shop/comment/list")
    public HttpResult findPage(@RequestParam(value = "pageNum")Integer pageNum,
                               @RequestParam(value = "pageSize")Integer pageSize,
                               @RequestParam(value = "shopId")Long shopId,
                               @RequestParam(value = "commentType")Integer commentType) {
        PageHelper.startPage(pageNum,pageSize);
        //0,全部 1，好评 2中评 3查评
        Page<CommentPageResultVo> page=deliveryCommentService.findPage(shopId,commentType);
        return HttpResult.ok(page);
    }

    @GetMapping(value = "/customer/shop/comment/count")
    public HttpResult shopCommentCount(@RequestParam(value = "shopId") Long shopId){

        return HttpResult.ok(deliveryCommentService.shopCommentCount(shopId));

    }
    @GetMapping(value = "/customer/order/reminder")
    public HttpResult remindOrder(@RequestParam(value = "orderNum") String orderNumber){
        return HttpResult.ok(deliveryOrderService.remindOrder(orderNumber));

    }
    @PostMapping(value = "/customer/order/pay")
    public HttpResult dealUnPayOrder(@RequestBody DeliveryOrderInfo deliveryOrderInfo){
        Integer amount=deliveryOrderService.findUnPayAmount(deliveryOrderInfo.getOrderNumber());
        BigDecimal payAmount=BigDecimal.valueOf(amount);
        payAmount=payAmount.divide(BigDecimal.valueOf(100));
        payAmount=payAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
        String subject ="diane wai mai";
        JSONObject jsonObject=PayUtil.payOrderAmount(deliveryOrderInfo.getCommand(),
                deliveryOrderInfo.getSubAppId(),
                deliveryOrderInfo.getOpenid(),
                payAmount,
                subject,
                deliveryOrderInfo.getOrderNumber(),
                NOTIFY_URL,
                null,
                null);
        return HttpResult.ok(jsonObject);
    }


}
