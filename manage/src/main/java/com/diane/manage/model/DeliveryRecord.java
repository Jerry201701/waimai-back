package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DeliveryRecord {
    private Long id;
    /*
    配送员id
     */
    private Long deliveryId;
    /*
    订单id
     */
    private Long orderId;
    /*
    订单编号
     */
    private String orderNumber;
    /*
    配送状态
    1待接单 2配送员已接单，3，配送中，4，配送成功 5，配送失败 6订单完成

     */
    private Integer status;
    private Timestamp createTime;
    private Integer deliveryStatus;


}
