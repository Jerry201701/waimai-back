package com.diane.manage.service;

import com.diane.manage.model.GoodCategory;
import com.diane.manage.vo.QueryCategoryVo;

import java.util.List;

public interface GoodCategoryService {
    int saveGoodCategory(GoodCategory goodCategory);
    int updateGoodCategory(GoodCategory goodCategory);
    List<QueryCategoryVo> listGoodCategory(GoodCategory goodCategory);
}
