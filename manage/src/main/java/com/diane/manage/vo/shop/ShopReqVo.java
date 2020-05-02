package com.diane.manage.vo.shop;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ShopReqVo {
    /*
    目标门店id
     */
    private Long shopId;
    /*
    源门店id
     */
    private Long coneShopId;
    private Timestamp createTime;

}
