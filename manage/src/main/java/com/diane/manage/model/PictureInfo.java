package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PictureInfo {
    /*
    图片编号
     */
    private Long id;
    /*
    图片类别
     */
    private Integer pictureType;
    /*
    图片名称
     */
    private String pictureDesc;
    /*
    图片存储地址
     */
    private String pictureUrl;
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
    图片关联id
     */
    private Long useId;
    /*
    是否逻辑删除
     */
    private Boolean flag;
    /*
    门店id
     */
    private Long shopId;
    /*
    商品id
     */
    private Long goodId;



}
