package com.diane.manage.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class DeliveryOrderInfo {
    private Long id;
    private Long addressId;
    /*
    是否拼团
     */
    private Boolean assemble;
    /*
    拼团编号
     */
    private Long assembleId;
    /*
    是否预约
     */
    private Boolean appointment;
    /*
    预约时间
     */
    private String appointmentTime;
    /*
    是否配送
     */
    private Boolean delivery;
    /*
   订单备注
     */
    private String remark;
    /*
    1,支付宝支付
    2，微信支付
    3，余额支付
     */
    private Integer payWay;
    private Long shopId;
    private String openid;
    /*
    支付状态：payStatus
    1,待支付，
    2，已支付，
    3，取消订单退款成功，
    4，取消订单退款失败
    5，无效订单退款成功
    6  无效订单退款失败
    7，部分退款成功
    8，部分退款失败
     */
    private Integer payStatus;
    private Integer amount;
    private Integer payAmount;
    private String orderNumber;
    private List<OrderGood> good;
    /*
  下单时间（已服务器时间为准）
   */
    private Timestamp orderTime;
    /*
    接口名称
    command
     */
    private String command;
    /*
    appid
     */
    private String subAppId;
    /*
    收货用户名
     */
    private String contact;
    /*
    收货电话
     */
    private String phone;
    /*
    收货详细地址
     */
    private String address;
    /*
    区域id
     */
    private  Long regionId;
    /*
    公众号支付成功跳转页面
     */
    private String paysucUrl;

    /*
    公众号支付失败跳转页面
     */
    private String payfailUrl;
    /*
    配送费
     */
    private Integer deliveryFee;

}
