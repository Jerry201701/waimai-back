package com.diane.manage.vo.delivery;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DeliveryOrderDetailVo {
    private String  orderNumber;
   private  String shopName;
   private  Integer distance;
    private String  address;
    private String phone;
    private  String receiver;
    private  String receiverPhone;
    private   String receiverAddress;
    private Integer receiverDistance;
    private Integer   frequency;
    private  String remark;
    private List<DeliveryGoodVo> good;
    private Timestamp createTime;
    private Timestamp fetchTime;
    private Timestamp  finishTime;
    private Integer payWay;
    /*
    3订单商户已接单；
    4骑手已抢单；
    5订单制作完成（打包完成）
    ；6订单配送中；
    7订单完成（701配送成功702配送失败）；
     */
    private Integer orderStatus;
    private Integer orderAmount;
    private Integer deliveryFee;
    private Integer deliveryFeeWay;
    private Long orderId;
    private Integer payStatus;
    private Integer handleStatus;
    private Integer deliveryStatus;
    private Boolean appointment;
    private String appointmentTime;

}
