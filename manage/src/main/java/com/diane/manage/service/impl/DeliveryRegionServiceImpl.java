package com.diane.manage.service.impl;

import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.DeliveryRegionMapper;
import com.diane.manage.model.DeliveryRegion;
import com.diane.manage.service.DeliveryRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeliveryRegionServiceImpl implements DeliveryRegionService {
    @Autowired
    private DeliveryRegionMapper deliveryRegionMapper;

    @Override
    public int saveDeliveryRegion(DeliveryRegion deliveryRegion) {
        return deliveryRegionMapper.insertSelective(deliveryRegion);
    }

    @Override
    public int updateDeliveryRegion(DeliveryRegion deliveryRegion) {
        return deliveryRegionMapper.updateByPrimaryKeySelective(deliveryRegion);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, deliveryRegionMapper);
    }

    @Override
    public int deleteDeliveryRegion(List<DeliveryRegion> list) {
        for (DeliveryRegion deliveryRegion:list){
            deliveryRegionMapper.deleteDeliveryRegion(deliveryRegion);
        }
        return list.size();
    }

    @Override
    public List<DeliveryRegion> listRegions() {
        return deliveryRegionMapper.listRegions();
    }
}
