package com.diane.manage.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class IntegralRecordVo {
    private String integralType;
    private Integer integralChange;
    private Timestamp integralTime;
    private Boolean changeDirection;
    private String    shopName;

}
