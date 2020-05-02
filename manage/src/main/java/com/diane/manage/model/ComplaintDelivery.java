package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ComplaintDelivery {
    private Long id;
    private Long deliveryId;
    private Long shopId;
    private String orderNum;
    /*
    1配送不及时
    2服务态度差
    3其他
     */
    private Integer type;
    private String content;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;

}
