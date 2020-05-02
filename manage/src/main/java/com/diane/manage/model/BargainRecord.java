package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BargainRecord {
    private Long id;
    private Long goodId;
    private Integer goodPrice;
    private String openid;
    private Long bargainId;
    private Timestamp bargainTime;
    private Timestamp lastUpdateTime;
    private Integer bargainPrice;
    private String  userPicUrl;
    private String  nickName;
    private Long activityId;
    private Integer cutOff;



}
