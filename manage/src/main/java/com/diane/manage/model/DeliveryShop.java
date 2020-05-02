package com.diane.manage.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class DeliveryShop {
    private Long id;
    private Long orderId;
    private Long shopId;
    private String orderNumber;
    private Timestamp handleTime;
    private Timestamp deliveryTime;
    /*
    支付状态：payStatus:1,待支付，
    2，已支付，
    3，取消订单退款成功，
    4，取消订单退款失败
     5，无效订单退款成功
     6  无效订单退款失败
     7，部分退款成功
     8，部分退款失败
     9，未支付且取消成功
     */
    private Integer payStatus;
    /*
    handleStatus，1待接单，
    2，已接单
    3，无效订单
    4，订单制作完成
    5，订单确认配送完成
    6，用户不满意要求退款
     */
    private Integer handleStatus;
    /*
    配送状态：deliveryStatus:
    1配送员已接单，
    2，配送中，
    3，配送成功
    4，配送失败
     */
    private Integer deliveryStatus;
    /*
    是否自动接单
     */
    private Boolean AutoReceipt;
    private Integer payAmount;
    private Integer refundAmount;
    /*
    传入支付平台的退款单号
     */
    private String refundNumber;
    /*
    配送员编号
     */
    private Long deliveryId;
    /*
    配送费
     */
    private Integer deliveryFee;
    /*
    商户id
     */
    private Long companyId;
    /*
    订单金额(不包括配送费部分)
     */
    private Integer amount;
}
