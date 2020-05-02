package com.diane.manage.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class NavInfo {
    private Long id;
    private Long regionId;
    private String navName;
    private String logoUrl;
    private String path;
    private String appid;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private Boolean flag;
    private String regionName;
    private Long createBy;
    private String remark;
    private String pathWeb;
    private String pathSmall;
    private Long categoryId;


}
