package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SubAdmin {
    private Long adminId;
    private Boolean flag;
    private String username;
    private String name;
    private Long shopId;
    private Long companyId;
    private String password;
    private String operatorPassword;
    private Long userId;
    private Long createBy;
    private Long lastUpdateBy;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private String shopName;
    private String subAdminName;
    private Long regionId;

}
