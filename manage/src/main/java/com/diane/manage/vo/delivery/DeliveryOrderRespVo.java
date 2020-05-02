package com.diane.manage.vo.delivery;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DeliveryOrderRespVo {
    private String orderNumber;
    private Timestamp orderTime;
    /*
    3订单商户已接单；
    4骑手已抢单；
    5订单制作完成（打包完成）；
    6订单配送中；7订单完成
    （701配送成功702配送失败）；
     */
    private String shopName;
    private Integer shopDistance;
    private String shopAddress;
    private String shopPhone;
    private String receiver;
    private String receiverPhone;
    private String receiverAddress;
    private Integer receiverDistance;
    private Integer payStatus;
    private Integer handleStatus;
    private Integer deliveryStatus;
    private Boolean appointment;
    private String appointmentTime;

}
