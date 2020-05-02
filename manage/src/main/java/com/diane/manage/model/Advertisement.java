package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Advertisement {
    private Long id;
    private Long regionId;
    private String path;
    private String remark;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private Long createBy;
    private Long lastUpdateBy;
    private Boolean flag;
    private String pictureUrl;
    private String advertisementName;
}
