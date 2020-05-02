package com.diane.manage.vo.shop;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderRemindVo {
    private String orderNumber;
    private String customerName;
    private String  phone;
    private String  address;
    private String amount;
    private String status;
    private String deliveryName;
    private String  deliveryPhone;
    private String orderTime;
    private String  shopId;
    private String shopName;
    private String  times;
    private String  counts;
    private String   delivery;
    private String pickCode;
    private Timestamp bookTime;
    private Integer payStatus;
    private Integer handleStatus;
    private Integer deliveryStatus;
    private Boolean appointment;
    private String appointmentTime;



}
