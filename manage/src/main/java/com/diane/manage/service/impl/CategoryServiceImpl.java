package com.diane.manage.service.impl;

import com.diane.common.page.ColumnFilter;
import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.CategoryInfoMapper;
import com.diane.manage.model.CategoryInfo;
import com.diane.manage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryInfoMapper categoryInfoMapper;
    @Override
    public int saveCategory(CategoryInfo categoryInfo) {
        return categoryInfoMapper.insertSelective(categoryInfo);
    }

    @Override
    public int updateCategory(CategoryInfo categoryInfo) {
        return categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilter("label");
        return MybatisPageHelper.findPage(pageRequest, categoryInfoMapper,"findPageByRegion",Long.valueOf(columnFilter.getValue()));
    }

    @Override
    public int deleteCategory(List<CategoryInfo> list) {
        for (CategoryInfo categoryInfo:list){
            categoryInfoMapper.deleteCategory(categoryInfo);
        }
        return list.size();
    }

    @Override
    public List<CategoryInfo> listAll() {
        return categoryInfoMapper.findPage();
    }

    @Override
    public List<CategoryInfo> listCategory(Long shopId) {
        return categoryInfoMapper.listCategory(shopId);
    }

    @Override
    public List<CategoryInfo> findCategoryByRegion(Long regionId) {
        return categoryInfoMapper.findCategoryByRegion(regionId);
    }
}
