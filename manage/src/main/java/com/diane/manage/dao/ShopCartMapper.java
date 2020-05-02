package com.diane.manage.dao;

import com.diane.manage.model.ShopCart;
import com.diane.manage.model.ShopCartGood;
import com.diane.manage.vo.AddressQuery;
import com.diane.manage.vo.ShopCartVo;
import com.diane.manage.vo.cart.CartResultVo;
import com.diane.manage.vo.cart.ShopGoodRespVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface ShopCartMapper {
    int insertSelective(ShopCart shopCart);
    int updateByPrimaryKeySelective(ShopCart shopCart);
    List<AddressQuery> listShopCartCondition(ShopCartGood shopCartGood);
    ShopCartVo showShopCartByUser(ShopCart shopCart);
    List<ShopGoodRespVo> shopCartList(@Param(value = "openid") String openid,
                                      @Param(value = "shopId") Long shopId);
    List<CartResultVo> listCartByOpenid(String openid);
    Integer countTotalPrice(@Param(value = "openid") String openid,
                            @Param(value = "shopId") Long shopId);
    CartResultVo singleShopCartInfo(@Param(value = "openid") String openid,
                                    @Param(value = "shopId") Long shopId);
    List<Long> findAllCartId(@Param(value = "openid") String openid,
                               @Param(value = "shopId") Long shopId);
    int cleanShopCart(@Param(value = "id") Long id);

}
