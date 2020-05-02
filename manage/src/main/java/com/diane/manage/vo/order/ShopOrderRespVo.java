package com.diane.manage.vo.order;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ShopOrderRespVo {
    //订单主键id
    private Long id;
    private  String orderNumber;
    private String  customerName;
    private  String phone;
    private   String address;
    private Integer amount;
    private  Integer   orderStatus;
    private String deliveryName;
    private  String  deliveryPhone;
    private Timestamp orderTime;
    private Long shopId;
    private  String  shopName;
    private Integer   times;
    private Integer   counts;
    private  Boolean  delivery;
    private  String    pickCode;
    private Timestamp bookTime;
    private Integer packCharges;
    private Integer deliveryFee;
    private Integer payStatus;
    private Integer handleStatus;
    private Integer deliveryStatus;
    private Boolean appointment;
    private String appointmentTime;


}
