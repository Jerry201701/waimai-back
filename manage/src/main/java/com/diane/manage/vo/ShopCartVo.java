package com.diane.manage.vo;

import com.diane.manage.model.GoodInfo;
import com.diane.manage.vo.cart.ShopGoodRespVo;
import lombok.Data;

import java.util.List;

@Data
public class ShopCartVo {
    private Long cartId;
    private String openid;
    private Long shopId;
    private Integer discount;
    private Integer totalPrice;
    private Integer packCharges;
    private String shopName;
    private Long goodId;
    private List<ShopGoodRespVo> goodInfoList;
    private GoodInfo goodInfo;
    //private GoodRespVo goodRespVo;


}
