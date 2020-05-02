package com.diane.manage.dao;

import com.diane.manage.model.Advertisement;
import com.diane.manage.model.DeliveryRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface AdvertisementMapper {
    int insertSelective(Advertisement advertisement);
    List<Advertisement> findPage(@Param(value = "regionId") Long regionId);
    int updateByPrimaryKeySelective(Advertisement advertisement);
    int deleteAdvertisement(Advertisement advertisement);
    List<Advertisement> listAdvertisement(@Param(value = "regionId")Long regionId);
}
