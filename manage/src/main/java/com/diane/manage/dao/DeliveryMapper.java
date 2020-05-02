package com.diane.manage.dao;

import com.diane.manage.model.Delivery;
import com.diane.manage.vo.delivery.DeliveryOrderRespVo;
import com.diane.manage.vo.delivery.DeliveryQueryVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface DeliveryMapper {
   int  insertSelective(Delivery delivery);
   int updateByPrimaryKeySelective(Delivery delivery);
   List<Delivery> findPage();
   int deleteDelivery(Delivery delivery);
   Delivery showDeliveryInfoById(@Param(value = "deliveryId") Long deliveryId);
   Page<DeliveryOrderRespVo> deliveryOrderList(DeliveryQueryVo deliveryQueryVo);
   Long findUserIdByDelivery(@Param(value = "deliveryId")Long deliveryId);
   Page<Delivery> findDeliveryByRegion(@Param(value = "regionId")Long regionId,
                                       @Param(value = "keyWord")String keyWord,
                                       @Param(value = "type")Integer type,
                                       @Param(value = "shopId")Long shopId);
}
