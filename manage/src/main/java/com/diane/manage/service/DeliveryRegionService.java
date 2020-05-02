package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.DeliveryRegion;

import java.util.List;

public interface DeliveryRegionService {
    int saveDeliveryRegion(DeliveryRegion deliveryRegion);
    int updateDeliveryRegion(DeliveryRegion deliveryRegion);
    PageResult findPage(PageRequest pageRequest);
    int deleteDeliveryRegion(List<DeliveryRegion> list);
    List<DeliveryRegion> listRegions();
}
