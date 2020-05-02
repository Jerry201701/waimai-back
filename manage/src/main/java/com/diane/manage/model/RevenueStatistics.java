package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RevenueStatistics {
    private Long id;
    private Long shopId;
    private Long companyId;
    /*
    商家收入
     */
    private Integer totalIncome;
    private Integer platFormReward;
    private Integer shopReward;
    private Integer deliveryReward;
    private Timestamp createTime;
    private String orderNumber;

}
