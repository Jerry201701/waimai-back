package com.diane.manage.service.impl;

import com.diane.manage.dao.DeliveryOrderMapper;
import com.diane.manage.model.DeliveryShop;
import com.diane.manage.vo.CallbackVo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
/*
支付回调中间件刷新订单状态
 */

@Component
@RabbitListener(queues = "mai")
public class PayCallbackService {
    @Autowired
    private DeliveryOrderMapper deliveryOrderMapper;
    @RabbitHandler
    public void confirmPayResult(CallbackVo callbackVo){
        if (callbackVo.getOrderNumber()==null){
            return;
        }
        try {

            DeliveryShop deliveryShop=deliveryOrderMapper.getPayAmountByOrderNumber(callbackVo.getOrderNumber());


            //如果商户设置自动接单，商户的接单数据就添加一条
            if (callbackVo.getStatus().equals("PAY_SUC")&&deliveryShop.getPayStatus()==1){
            if(deliveryShop.getAutoReceipt()!=null&&deliveryShop.getAutoReceipt()){
               deliveryShop.setHandleTime(new Timestamp(System.currentTimeMillis()));
                deliveryShop.setHandleStatus(2);
                deliveryShop.setDeliveryStatus(5);

            }else {
            deliveryShop.setHandleStatus(1);
            }
            deliveryShop.setPayStatus(2);
            deliveryShop.setDeliveryStatus(5);
            deliveryOrderMapper.changeOrderStatus(deliveryShop);
                System.out.println("中间件更新订单成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
