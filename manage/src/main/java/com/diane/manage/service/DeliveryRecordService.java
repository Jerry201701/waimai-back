package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.manage.model.DeliveryRecord;
import com.diane.manage.model.DeliveryShop;
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

import java.util.List;

public interface DeliveryRecordService {
    Page<DeliveryOrderRespVo> deliveryOrderList(DeliveryQueryVo deliveryQueryVo);
    DeliveryOrderDetailVo deliveryOrderDetail(String orderNumber);
    int saveDeliveryRecord(DeliveryRecord deliveryRecord);
    int manageDeliveryStatus(String orderNumber,Integer deliveryStatus,Long deliveryId);
    Page<DeliveryOrderRespVo> listDeliveryOrders(DeliveryQueryVo deliveryQueryVo);
    List<OrderRemindVo> orderRemindList(Long shopId,Long companyId);
    Page<IntegralRecordVo> countAmountByCustomer(String openid);
    Page<ConsumptionRecordVo>listConsumptionRecord(String openid,String comsumeTime,Integer payWay);
    Integer countTotalAmount(String openid, String comsumeTime, Integer payWay);

    Page<ShopOrderRespVo> listShopOrderByManager(PageRequest pageRequest);
    ManageOrderRespVo showOrderDetailByManager(Long id);
    CountOrderVo orderCounts(String openid ,Long regionId);







}
