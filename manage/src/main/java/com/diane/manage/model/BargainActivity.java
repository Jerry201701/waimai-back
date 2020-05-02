package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BargainActivity {
    private Long id;
    private Long goodId;
    private Long activityId;
    private String openid;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private Integer currentPrice;
}
