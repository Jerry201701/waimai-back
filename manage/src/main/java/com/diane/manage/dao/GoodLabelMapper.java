package com.diane.manage.dao;

import com.diane.manage.model.GoodLabel;
import com.diane.manage.vo.good.CopyGoodVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface GoodLabelMapper {
    int insertSelective(GoodLabel goodLabel);
    int updateByPrimaryKeySelective(GoodLabel goodLabel);
    int deleteLabelByGood(@Param(value = "id") Long id);
    List<Long> listLabelByGoodId(@Param(value = "goodId") Long goodId);
    int copyGoodLabel(CopyGoodVo copyGoodVo);
}
