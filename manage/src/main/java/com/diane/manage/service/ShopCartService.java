package com.diane.manage.service;

import com.diane.manage.model.GoodInfo;
import com.diane.manage.model.ShopCart;
import com.diane.manage.model.ShopCartGood;
import com.diane.manage.vo.ShopCartVo;
import com.diane.manage.vo.cart.CartResultVo;
import com.diane.manage.vo.cart.ShopGoodRespVo;


import java.util.List;

public interface ShopCartService {
    Long insertSelective(ShopCart shopCart);
    int updateByPrimaryKeySelective(ShopCart shopCart);
    List<ShopCart> listShopCartCondition(ShopCartGood shopCartGood);
    ShopCartVo showShopCartDetail(ShopCart shopCart);
    List<GoodInfo> listShopGood(Long shopId,Long categoryId);
    List<ShopGoodRespVo> shopCartList(ShopCart shopCart);
    List<CartResultVo>  listCartByOpenid(String openid);
    CartResultVo singleShopCartInfo(String openid,Long shopId);
    List<Long> findAllCartId(String openid,Long shopId);
    int cleanShopCart(Long id);




}
