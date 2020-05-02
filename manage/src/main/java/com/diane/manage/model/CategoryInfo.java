package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CategoryInfo {
    private Long id;
    private String categoryName;
    private String label;
    private String remark;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private Long createBy;
    private Long lastUpdateBy;
    private Boolean flag;
    private String logoUrl;
    /*
    1,门店商品种类
    2，学校门店种类
     */
    private Integer categoryType;
    private Long shopId;
    /*
    区域id
     */
    private Long regionId;
    private String regionName;

}
