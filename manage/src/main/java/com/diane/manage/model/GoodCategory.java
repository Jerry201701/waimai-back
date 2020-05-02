package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GoodCategory {
    private Long id;
    private String categoryName;
    private Long shopId;
    /*
   创建时间
    */
    private Timestamp createTime;
    /*
    修改时间
     */
    private Timestamp lastUpdateTime;
    /*
    创建人
     */
    private Long createBy;
    /*
    修改人
     */
    private Long lastUpdateBy;
    /*
    逻辑删除
     */
    private Boolean flag;
}
