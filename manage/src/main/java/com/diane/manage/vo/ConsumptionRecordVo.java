package com.diane.manage.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ConsumptionRecordVo {
    private String shopName;
    private Long   shopId;
    private Timestamp comsumeTime;
    private Integer amount;
    private Integer integral;

}
