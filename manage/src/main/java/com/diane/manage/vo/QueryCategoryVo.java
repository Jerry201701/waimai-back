package com.diane.manage.vo;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class QueryCategoryVo {
    private Long categoryId;
    private String categoryName;
    private String label;
    private String remark;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private Long createBy;
    private Long lastUpdateBy;
    private Boolean flag;
    private String logoUrl;
    private Integer categoryType;
    private Long shopId;

}
