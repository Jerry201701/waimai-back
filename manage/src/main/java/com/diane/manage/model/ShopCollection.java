package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ShopCollection {
    private Long id;
    private String openid;
    private Long shopId;
    private Integer type;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer collectionType;
    private  Integer pageNum;
    private Integer pageSize;
    private Boolean flag;
    private Long regionId;




}
