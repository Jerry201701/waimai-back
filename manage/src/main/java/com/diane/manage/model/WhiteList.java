package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WhiteList {
    private Long id;
    private Long shopId;
    private Long deliveryId;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private Boolean flag;
}
