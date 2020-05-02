package com.diane.manage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.diane.manage.dao.DeliveryShopMapper;
import com.diane.manage.dao.OrderGoodMapper;
import com.diane.manage.model.DeliveryShop;
import com.diane.manage.service.DeliveryShopService;
import com.diane.manage.util.PayUtil;
import com.diane.manage.vo.OrderInfoVo;
import com.diane.manage.vo.order.OrderManagementVo;
import com.diane.manage.vo.order.OrderQueryInfo;
import com.diane.manage.vo.order.PayResultDetail;
import com.diane.manage.vo.order.ShopOrderRespVo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryShopServiceImpl implements DeliveryShopService  {
    private static  final String PAY_SUCCESS="PAY_SUC";
    private static final  String REFUND_SUCCESS="REFUND";
    @Autowired
    private DeliveryShopMapper deliveryShopMapper;
    @Autowired
    private OrderGoodMapper orderGoodMapper;
    @Override
    public int insertSelective(DeliveryShop deliveryShop) {
        return deliveryShopMapper.insertSelective(deliveryShop);
    }

    @Override
    public int updateByPrimaryKeySelective(DeliveryShop deliveryShop) {
        return deliveryShopMapper.updateByPrimaryKeySelective(deliveryShop);
    }

    @Override
    public Page<ShopOrderRespVo> listShopOrder(OrderQueryInfo queryInfo) {
        return deliveryShopMapper.listShopOrder(queryInfo);
    }

    @Override
    public int changeHandleStatus(String orderNumber,Integer status) {
        return deliveryShopMapper.changeHandleStatus(orderNumber,status);
    }

    @Override
    public OrderInfoVo deliveryOrderDetailByNumber(String orderNumber) {
        OrderInfoVo orderInfoVo=deliveryShopMapper.deliveryOrderDetailByNumber(orderNumber);
        orderInfoVo.setGood(orderGoodMapper.listGoodByOrder(orderInfoVo.getOrderId()));


        if (orderInfoVo.getPayStatus()==1){
            PayResultDetail payResultDetail= JSONObject.parseObject(PayUtil.getOrderPayStatus(orderInfoVo.getOrderNumber()).getString("body"), PayResultDetail.class);
            if (payResultDetail.getOrderStatus().equals(PAY_SUCCESS)){
                orderInfoVo.setPayStatus(2);
            }
//            if (payResultDetail.getOrderStatus().equals(REFUND_SUCCESS)){
//                orderInfoVo.setOrderStatus(11);
//            }
        }

        return orderInfoVo;
    }
}
