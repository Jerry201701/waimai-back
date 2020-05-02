package com.diane.manage.service;

import com.diane.manage.model.FullReduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FullReductionService {
    int insertSelective(FullReduction fullReduction);
    int updateByPrimaryKeySelective(FullReduction fullReduction);
    List<FullReduction> listFullReductionByShop(Long shopId);
    int removeRecord(Long activityId);
    FullReduction findSingleFullReductionByShop(Long shopId,Integer amount);
}
