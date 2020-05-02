package com.diane.manage.service;

import com.diane.manage.model.DeliveryOrderInfo;
import com.diane.manage.model.DeliveryShop;
import com.diane.manage.model.OrderGood;
import com.diane.manage.vo.IntegralRecordVo;
import com.diane.manage.vo.ManagerQueryVo;
import com.diane.manage.vo.OrderInfoVo;
import com.diane.manage.vo.delivery.StatisticsDeliveryVo;
import com.diane.manage.vo.order.OrderQueryInfo;
import com.diane.manage.vo.order.ShopOrderRespVo;
import com.diane.manage.vo.shop.ShopDispatchVo;
import com.diane.manage.vo.shop.ShopStatisticsRespVo;
import com.diane.manage.vo.shop.ShopStatisticsVo;
import com.github.pagehelper.Page;

import java.math.BigDecimal;
import java.util.List;

public interface DeliveryOrderService {
    int createDeliveryOrder(DeliveryOrderInfo deliveryOrderInfo);
    List<OrderInfoVo> deliveryOrderList(OrderQueryInfo orderQueryInfo);
    int changeOrderStatus (DeliveryShop deliveryShop);
    Integer showGoodLabelPrice(OrderGood orderGood);
    Integer showGoodPrice(OrderGood orderGood);
    OrderInfoVo getOrderDetail(Long orderId);
    Page<ShopOrderRespVo> listOrderByShop(OrderQueryInfo queryInfo);
    OrderInfoVo deliveryOrderDetailByNumber(String orderNumber);
    DeliveryShop getPayAmountByOrderNumber(String orderNumber);
    int refundManagement(String orderNumber, String reason, Integer refundAmount);
    int customerCancelOrder(String orderNumber,String reason,BigDecimal refundAmount);
    int remindOrder(String orderNumber);
    List<StatisticsDeliveryVo> deliveryStatistics(Long deliveryId,String deliveryTime );
    int dispatchDelivery(ShopDispatchVo shopDispatchVo);
    ShopStatisticsRespVo shopStatistics(ShopStatisticsVo shopStatisticsVo);
    Integer findUnPayAmount(String orderNumber);
    Page<ShopStatisticsRespVo> managerShopStatistics(ManagerQueryVo managerQueryVo);

}
