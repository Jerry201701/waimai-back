package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Wallet {
    private Long id;
    /*
    1,商户 0，骑手
     */
    private Boolean roleType;
    private Long companyId;
    private Long deliveryId;
    /*
    钱包余额
     */
    private Integer balance;
    private Boolean flag;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private String openid;
    private Long shopId;

}
