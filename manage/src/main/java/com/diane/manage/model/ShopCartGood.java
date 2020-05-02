package com.diane.manage.model;

import lombok.Data;

@Data
public class ShopCartGood {
    private Long id;
    private Long shopCartId;
    private Long goodId;
    private Integer quantity;

    public ShopCartGood(Long goodId,Long shopCartId, Integer quantity) {
        this.goodId = goodId;
        this.quantity = quantity;
        this.shopCartId=shopCartId;
    }
}
