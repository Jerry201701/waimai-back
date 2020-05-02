package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AssembleRecord {
    private Long id;
    private Long goodId;
    private Long activityId;
    private Long assembleId;
    private String openid;
    private Integer assemblePrice;
    private Timestamp createTime;
}
