package com.diane.manage.service.impl;

import com.diane.common.page.PageRequest;
import com.diane.manage.dao.DeliveryOrderMapper;
import com.diane.manage.dao.DeliveryRecordMapper;
import com.diane.manage.dao.OrderGoodMapper;
import com.diane.manage.model.DeliveryRecord;
import com.diane.manage.model.DeliveryShop;
import com.diane.manage.service.DeliveryRecordService;
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
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {
    @Autowired
    private DeliveryRecordMapper deliveryRecordMapper;
    @Autowired
    private OrderGoodMapper orderGoodMapper;
    @Autowired
    private DeliveryOrderMapper deliveryOrderMapper;


    @Override
    public Page<DeliveryOrderRespVo> deliveryOrderList(DeliveryQueryVo deliveryQueryVo) {
        return deliveryRecordMapper.deliveryOrderList(deliveryQueryVo);
    }

    @Override
    public DeliveryOrderDetailVo deliveryOrderDetail(String orderNumber) {
        DeliveryOrderDetailVo deliveryOrderDetailVo=deliveryRecordMapper.deliveryOrderDetail(orderNumber);
        deliveryOrderDetailVo.setGood(orderGoodMapper.listOrderGood(deliveryOrderDetailVo.getOrderId()));

        return deliveryOrderDetailVo;
    }


    @Override
    public int saveDeliveryRecord(DeliveryRecord deliveryRecord) {
        //deliveryRecordMapper.insertSelective(deliveryRecord);
        return deliveryRecordMapper.insertSelective(deliveryRecord);
    }

    @Override
    public int manageDeliveryStatus(String orderNumber,Integer deliveryStatus,Long deliveryId) {
        Integer status=deliveryOrderMapper.findDeliveryStatus(orderNumber);
        //抢单
        if (status==5&&deliveryStatus==1){
            return deliveryOrderMapper.manageDeliveryStatus(orderNumber,deliveryStatus,deliveryId);
        }
        //修改为配送中
        if (status==1&&deliveryStatus==2){
            return deliveryOrderMapper.manageDeliveryStatus(orderNumber,deliveryStatus,deliveryId);
        }
        //修改为配送成功
        if (status==2&&deliveryStatus==3){
            return deliveryOrderMapper.manageDeliveryStatus(orderNumber,deliveryStatus,deliveryId);
        }
        //修改为配送失败
        if (status==2&&deliveryStatus==4){
            return deliveryOrderMapper.manageDeliveryStatus(orderNumber,deliveryStatus,deliveryId);
        }
        return  0;

    }

    @Override
    public Page<DeliveryOrderRespVo> listDeliveryOrders(DeliveryQueryVo deliveryQueryVo) {
        return deliveryRecordMapper.listDeliveryOrders(deliveryQueryVo);
    }

    @Override
    public List<OrderRemindVo> orderRemindList(Long shopId,Long companyId) {
        return deliveryRecordMapper.orderRemindList(shopId,companyId);
    }

    @Override
    public Page<IntegralRecordVo> countAmountByCustomer(String openid) {
        return deliveryRecordMapper.countAmountByCustomer(openid);
    }

    @Override
    public Page<ConsumptionRecordVo> listConsumptionRecord(String openid, String comsumeTime,Integer payWay) {
        return deliveryRecordMapper.listConsumptionRecord(openid,comsumeTime,payWay);
    }

    @Override
    public Integer countTotalAmount(String openid, String comsumeTime, Integer payWay) {
        return deliveryRecordMapper.countTotalAmount(openid,comsumeTime,payWay);
    }

    @Override
    public Page<ShopOrderRespVo> listShopOrderByManager(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());


        return deliveryRecordMapper.listOrderByManager();
    }


    @Override
    public CountOrderVo orderCounts(String openid, Long regionId) {
        CountOrderVo countOrderVo=new CountOrderVo();
        countOrderVo.setWaitcomment(deliveryRecordMapper.countWaitComment(openid,regionId));
        countOrderVo.setWaitpay(deliveryRecordMapper.countWaitPay(openid,regionId));
        countOrderVo.setWaitreceive(deliveryRecordMapper.countWaitReceive(openid,regionId));
        return countOrderVo;
    }

    @Override
    public ManageOrderRespVo showOrderDetailByManager(Long id) {
        return deliveryRecordMapper.showOrderDetailByManager(id);
    }
}
