package com.diane.manage.vo.cart;

import lombok.Data;

import java.util.List;

@Data
public class ShopGoodRespVo {
    private Long goodId;
    private String goodName;
    private Long cartId;
    private String unit;
    private String goodCategory;
    private Integer price;
    private Integer oldPrice;
    private Integer quantity;
    private List<PictureRespVo> pictureRespVoList;
    private Long categoryId;
    private String categoryName;
    private Long labelId;
    private String labelName;

}
