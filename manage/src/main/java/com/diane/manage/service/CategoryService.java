package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.CategoryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryService {
    int saveCategory(CategoryInfo categoryInfo);
    int updateCategory(CategoryInfo categoryInfo);
    PageResult findPage(PageRequest pageRequest);
    int deleteCategory(List<CategoryInfo> list);
    List<CategoryInfo> listAll();
    List<CategoryInfo> listCategory(Long shopId);
    List<CategoryInfo>  findCategoryByRegion(Long regionId);
}
