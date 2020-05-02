package com.diane.manage.dao;

import com.diane.manage.model.CategoryInfo;
import com.diane.manage.model.ShopCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ShopCategoryMapper {
   int  insertSelective(ShopCategory shopCategory);
   int updateByPrimaryKeySelective(ShopCategory shopCategory);
   List<CategoryInfo> listCategoryByShop(@Param(value = "shopId")Long shopId);


}
