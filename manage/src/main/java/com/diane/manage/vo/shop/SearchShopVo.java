package com.diane.manage.vo.shop;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class SearchShopVo {
    private  Long shopId;
    private List<String> shopPicUrl;
    private String shopName;
    private BigDecimal monthSale;
    private Timestamp deliveryTime;
    private BigDecimal basePrice;
    private BigDecimal deliveryFee;
    private BigDecimal selfFee;
    private String discount;
    private BigDecimal shopStars;

}
