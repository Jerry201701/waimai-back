package com.diane.manage.vo.shop;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ShopCollectionRespVo {
    private Long collectionId;
    private Long shopId;
    private String shopName;
    private List<String> shopPicUrl;
    private Integer shopStars;
    private Integer salesNumber;
    private BigDecimal startPrice;
    private BigDecimal   deliveryFee;
    private Long goodId;
    private List<String>  goodPic;
    private  BigDecimal price;





}
