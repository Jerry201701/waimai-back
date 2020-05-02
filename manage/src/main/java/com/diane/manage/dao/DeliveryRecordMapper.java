package com.diane.manage.dao;

import com.diane.manage.model.Delivery;
import com.diane.manage.model.DeliveryRecord;
import com.diane.manage.vo.ConsumptionRecordVo;
import com.diane.manage.vo.IntegralRecordVo;
import com.diane.manage.vo.delivery.DeliveryOrderDetailVo;
import com.diane.manage.vo.delivery.DeliveryOrderRespVo;
import com.diane.manage.vo.delivery.DeliveryQueryVo;
import com.diane.manage.vo.order.CountOrderVo;
import com.diane.manage.vo.order.ManageOrderRespVo;
import com.diane.manage.vo.order.ShopOrderRespVo;
import com.diane.manage.vo.shop.OrderRemindVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DeliveryRecordMapper {
    int  insertSelective(DeliveryRecord record);
    int updateByPrimaryKeySelective(DeliveryRecord record);
    Page<DeliveryOrderRespVo> deliveryOrderList(DeliveryQueryVo deliveryQueryVo);
    DeliveryOrderDetailVo deliveryOrderDetail(@Param(value = "orderNumber")String orderNumber);
    int manageDeliveryStatus(DeliveryRecord deliveryRecord);
    Page<DeliveryOrderRespVo> listDeliveryOrders(DeliveryQueryVo deliveryQueryVo);
    List<OrderRemindVo> orderRemindList(@Param(value = "shopId")Long shopId,@Param(value = "companyId")Long companyId);
    Page<IntegralRecordVo> countAmountByCustomer(@Param(value = "openid")String openid);
    Page<ConsumptionRecordVo>listConsumptionRecord(@Param(value = "openid")String openid,
                                                   @Param(value = "comsumeTime")String comsumeTime,
                                                   @Param(value = "payWay")Integer payWay);

    Integer countTotalAmount(@Param(value = "openid")String openid,
                             @Param(value = "comsumeTime")String comsumeTime,
                             @Param(value = "payWay")Integer payWay);

    Page<ShopOrderRespVo> listOrderByManager();
    ManageOrderRespVo showOrderDetailByManager(@Param(value = "id")Long id);

    Integer countWaitPay(@Param(value = "openid") String openid,@Param(value = "regionId")Long regionId);
     Integer countWaitReceive(@Param(value = "openid") String openid,@Param(value = "regionId")Long regionId);
    Integer countWaitComment(@Param(value = "openid") String openid,@Param(value = "regionId")Long regionId);


}
