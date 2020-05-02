package com.diane.manage.dao;

import com.diane.manage.model.CategoryInfo;
import com.diane.manage.vo.category.CopyCategoryVo;
import com.diane.manage.vo.shop.ShopReqVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import sun.awt.SunHints;

import java.util.List;
@Component
@Mapper
public interface CategoryInfoMapper {
    int insertSelective(CategoryInfo categoryInfo);
    List<CategoryInfo> findPage();
    List<CategoryInfo> findPageByRegion(@Param(value = "regionId")Long regionId);
    int updateByPrimaryKeySelective(CategoryInfo categoryInfo);
    int deleteCategory(CategoryInfo categoryInfo);
    List<CategoryInfo> listCategory(@Param(value = "shopId")Long shopId);
    List<CategoryInfo>  findCategoryByRegion(@Param(value = "regionId") Long regionId);
    int copyGoodCategory(ShopReqVo shopReqVo);
    List<CopyCategoryVo> listSourceByShop(@Param(value = "shopId")Long shopId);
}
