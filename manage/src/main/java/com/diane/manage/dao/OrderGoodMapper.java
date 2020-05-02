package com.diane.manage.dao;

import com.diane.manage.model.OrderGood;
import com.diane.manage.vo.delivery.DeliveryGoodVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface OrderGoodMapper {
    int insertSelective(OrderGood orderGood);
    int updateByPrimaryKeySelective(OrderGood orderGood);
    List<OrderGood> listGoodByOrder(@Param(value = "orderId") Long orderId);
    List<DeliveryGoodVo> listOrderGood(@Param(value = "orderId") Long orderId);
}
