package com.diane.manage.dao;

import com.diane.manage.model.ShopCart;
import com.diane.manage.model.ShopCartGood;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface ShopCartGoodMapper {
    int insertSelective(ShopCartGood shopCartGood);
    int updateByPrimaryKeySelective(ShopCartGood shopCartGood);
    List<ShopCartGood> listShopCartGoodCondition(ShopCartGood shopCartGood);
    int removeCartInfo(ShopCart shopCart);

}
