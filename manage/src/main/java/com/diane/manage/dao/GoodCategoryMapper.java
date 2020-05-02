package com.diane.manage.dao;

import com.diane.manage.model.GoodCategory;
import com.diane.manage.vo.QueryCategoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface GoodCategoryMapper {
    int insertSelective(GoodCategory goodCategory);
    List<GoodCategory> findPage();
    int updateByPrimaryKeySelective(GoodCategory goodCategory);
    int deleteGoodCategory(GoodCategory goodCategory);
    List<QueryCategoryVo> listGoodCategory(GoodCategory goodCategory);
}
