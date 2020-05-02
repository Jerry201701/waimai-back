package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.Delivery;
import com.diane.manage.vo.delivery.DeliveryOrderRespVo;
import com.diane.manage.vo.delivery.DeliveryQueryVo;
import com.github.pagehelper.Page;

import java.util.List;

public interface DeliveryService {
    Long  insertSelective(Delivery delivery);
    int updateByPrimaryKeySelective(Delivery delivery);
    List<Delivery> findPage();
    int deleteDelivery(Delivery delivery);
    Delivery showDeliveryInfoById(Long deliveryId);
    PageResult findPage(PageRequest pageRequest);
    Long deliveryOrderManage(DeliveryQueryVo deliveryQueryVo);
    Page<Delivery> findDeliveryByRegion(Long regionId,String keyWord,Integer type,Long shopId);

}
