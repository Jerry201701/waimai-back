package com.diane.manage.service;

import com.diane.manage.model.DeliveryShop;
import com.diane.manage.vo.OrderInfoVo;
import com.diane.manage.vo.order.OrderManagementVo;
import com.diane.manage.vo.order.OrderQueryInfo;
import com.diane.manage.vo.order.ShopOrderRespVo;
import com.github.pagehelper.Page;


public interface DeliveryShopService {
    int  insertSelective(DeliveryShop deliveryShop);
    int updateByPrimaryKeySelective(DeliveryShop deliveryShop);
    Page<ShopOrderRespVo> listShopOrder(OrderQueryInfo queryInfo);
    int changeHandleStatus(String orderNumber,Integer handleStatus);
    OrderInfoVo deliveryOrderDetailByNumber(String orderNumber);
}
