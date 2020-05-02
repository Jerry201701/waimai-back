package com.diane.manage.dao;

import com.diane.manage.model.ShopGood;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ShopGoodMapper {
    int insertSelective(ShopGood shopGood);
    int updateByPrimaryKeySelective(ShopGood shopGood);
}
