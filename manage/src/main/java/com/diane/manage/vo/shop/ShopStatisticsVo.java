package com.diane.manage.vo.shop;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ShopStatisticsVo {
    private Timestamp beginTime;
    private Timestamp endTime;
    private Long shopId;
    private Integer orderStatus;
    private Integer orderType;

}
