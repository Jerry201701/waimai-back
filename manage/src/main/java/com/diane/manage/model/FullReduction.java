package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FullReduction {
    /*
    活动id
     */
    private Long activeId;
    /*
    活动门槛
     */
    private Integer limitPrice;
    /*
    优惠价格
     */
    private Integer price;
    /*
    门店id
     */
    private Long   shopId;
    /*
    是否删除
     */
    private Boolean flag;
    /*
    活动名称
     */
    private String activeName;
    /*
    活动开始
     */
    private Timestamp startDate;
    /*
    活动结束
     */
    private Timestamp  endDate;
    /*
    创建时间
     */
    private Timestamp createTime;
    /*
    修改时间
     */
    private Timestamp lastUpdateTime;

}
