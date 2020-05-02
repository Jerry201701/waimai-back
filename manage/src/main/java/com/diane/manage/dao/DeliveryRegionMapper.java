package com.diane.manage.dao;

import com.diane.manage.model.DeliveryRegion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface DeliveryRegionMapper {
    int insertSelective(DeliveryRegion deliveryRegion);
    List<DeliveryRegion> findPage();
    int updateByPrimaryKeySelective(DeliveryRegion deliveryRegion);
    int deleteDeliveryRegion(DeliveryRegion deliveryRegion);
    List<DeliveryRegion> listRegions();
}
