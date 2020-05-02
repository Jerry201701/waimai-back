package com.diane.manage.vo.shop;

import lombok.Data;

import java.util.List;

@Data
public class ShopBasicInfoRespVo {
    private Long shopId;
    private Long regionId;
    private String shopName;
    private List<String> shopPicUrl;
    private Integer balance;
    private Integer income;
    private Integer orderNumber;
    private Integer unhandle;
    private Integer storeNumber;
    private Integer goodNumber;
    private Integer withdrawableAmount;

}
