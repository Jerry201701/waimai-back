package com.diane.manage.dao;

import com.diane.manage.model.DeliveryOrderInfo;
import com.diane.manage.model.DeliveryShop;
import com.diane.manage.model.OrderGood;
import com.diane.manage.vo.ManagerQueryVo;
import com.diane.manage.vo.OrderInfoVo;
import com.diane.manage.vo.delivery.StatisticsDeliveryVo;
import com.diane.manage.vo.order.DeliveryOrderStatusVo;
import com.diane.manage.vo.order.OrderQueryInfo;
import com.diane.manage.vo.order.ShopOrderRespVo;
import com.diane.manage.vo.shop.ShopDispatchVo;
import com.diane.manage.vo.shop.ShopStatisticsRespVo;
import com.diane.manage.vo.shop.ShopStatisticsVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DeliveryOrderMapper {
    int insertSelective(DeliveryOrderInfo deliveryOrderInfo);

    List<OrderInfoVo> deliveryOrderList(OrderQueryInfo orderQueryInfo);
    int changeOrderStatus(DeliveryShop deliveryShop);
    Integer showGoodLabelPrice(OrderGood orderGood);
    Integer showGoodPrice(OrderGood orderGood);
    OrderInfoVo deliveryOrderDetail(@Param(value = "orderId") Long orderId);
    Page<ShopOrderRespVo> listOrderByShop(OrderQueryInfo queryInfo);
    OrderInfoVo deliveryOrderDetailByNumber(@Param(value = "orderNumber")String orderNumber);
    DeliveryShop getPayAmountByOrderNumber(@Param(value ="orderNumber")String orderNumber);
    Integer findDeliveryStatus(@Param(value = "orderNumber") String orderNumber);
    int  manageDeliveryStatus(@Param(value = "orderNumber")String orderNumber,
                              @Param(value = "deliveryStatus") Integer deliveryStatus,
                              @Param(value = "deliveryId")Long deliveryId);

    int remindOrder(@Param(value = "orderNumber")String orderNumber);
    Integer findOrderIncome(@Param(value = "shopId")Long shopId);
    List<StatisticsDeliveryVo> deliveryStatistics(@Param(value = "deliveryId")Long deliveryId,@Param(value = "deliveryTime") String deliveryTime );

    Integer findSuccessNumber(@Param(value = "deliveryId")Long deliveryId,@Param(value = "deliveryTime") String deliveryTime );
    int dispatchDelivery(ShopDispatchVo shopDispatchVo);
    ShopStatisticsRespVo shopStatistics(ShopStatisticsVo shopStatisticsVo);
    Integer findUnPayAmount(@Param(value = "orderNumber")String orderNumber);
    Page<ShopStatisticsRespVo> managerShopStatistics(ManagerQueryVo managerQueryVo);


}
