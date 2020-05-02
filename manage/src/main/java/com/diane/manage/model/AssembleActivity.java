package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AssembleActivity {
    private Long assembleId;
    private Long goodId;
    private Long activityId;
    private String openid;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private Integer assemblePrice;
    private Boolean leader;
    private Long superiorId;


}
