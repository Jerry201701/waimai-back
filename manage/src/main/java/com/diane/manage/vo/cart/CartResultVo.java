package com.diane.manage.vo.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartResultVo {
    private  Long shopId;
    private  String shopName;
    private Integer discount;
    private Integer deliveryFee;
    private Integer totalPrice;
    private Integer packCharges;
    private List<ShopGoodRespVo> goodList;



}
