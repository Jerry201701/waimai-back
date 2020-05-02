package com.diane.manage.vo.shop;

import lombok.Data;

@Data
public class ShopStatisticsRespVo {
    private  Integer turnover;
    private String orderNumber;
    private Integer refund;
    private Long shopId;
    private Integer incomeNumber;
    private Integer  successCount;
    private Integer failCount;
    private String companyName;
    private String shopName;

}
