package com.diane.manage.dao;

import com.diane.manage.model.DeliveryShop;
import com.diane.manage.vo.OrderInfoVo;
import com.diane.manage.vo.order.OrderManagementVo;
import com.diane.manage.vo.order.OrderQueryInfo;
import com.diane.manage.vo.order.ShopOrderRespVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DeliveryShopMapper {
    int  insertSelective(DeliveryShop deliveryShop);
    int updateByPrimaryKeySelective(DeliveryShop deliveryShop);
    Page<ShopOrderRespVo> listShopOrder(OrderQueryInfo queryInfo);
    int changeHandleStatus(@Param("orderNumber")String orderNumber,@Param("handleStatus")Integer handleStatus);
    OrderInfoVo deliveryOrderDetailByNumber(@Param(value = "orderNumber")String orderNumber);

}
