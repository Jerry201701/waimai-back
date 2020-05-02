package com.diane.manage.service.impl;

import com.diane.manage.dao.GoodCategoryMapper;
import com.diane.manage.model.GoodCategory;
import com.diane.manage.service.GoodCategoryService;
import com.diane.manage.vo.QueryCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodCategoryServiceImpl implements GoodCategoryService {
    @Autowired
    private GoodCategoryMapper goodCategoryMapper;
    @Override
    public int saveGoodCategory(GoodCategory goodCategory) {
        return goodCategoryMapper.insertSelective(goodCategory);
    }

    @Override
    public int updateGoodCategory(GoodCategory goodCategory) {
        return goodCategoryMapper.updateByPrimaryKeySelective(goodCategory);
    }

    @Override
    public List<QueryCategoryVo> listGoodCategory(GoodCategory goodCategory) {
        return goodCategoryMapper.listGoodCategory(goodCategory);
    }
}
