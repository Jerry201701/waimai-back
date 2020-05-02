package com.diane.manage.vo;

import com.diane.manage.model.OrderGood;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderInfoVo {
    private Long orderId;
    private String shopName;
    private String orderNumber;
    private List<OrderGood> good;
    private List<String> shopPicUrl;
    private Integer goodQuantity;
    private Integer amount;
    private Long shopId;
    private Timestamp orderTime;
    private Integer payAmount;
    private Integer payWay;
    private Timestamp payTime;
    private Timestamp sendTime;
    private Timestamp finishTime;
    private Integer refundAmount;
    private Integer deliveryFee;
    private Boolean comment;
    private Boolean canRefund;
    private Long assembleId;
    private Integer leftCount;
    private Timestamp assembleEndTime;
    private Integer payStatus;
    private Boolean delivery;
    private String receiverAddress;
    private String receiverContact;
    private String receiverPhone;
    private Long deliveryId;
    private String deliveryName;
    private String deliveryPhone;
    private Integer packCharges;
    private String address;
    private String pickCode;
    private Long regionId;
    private Boolean appointment;
    private String appointmentTime;

    private Integer handleStatus;
    private Integer deliveryStatus;
    private Integer totalIncome;
    private Integer platFormReward;
    private Integer shopReward;
    private Integer deliveryReward;



}
