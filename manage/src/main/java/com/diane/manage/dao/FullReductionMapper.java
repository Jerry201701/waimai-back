package com.diane.manage.dao;

import com.diane.manage.model.FullReduction;
import com.diane.manage.vo.AddressQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FullReductionMapper {

    int insertSelective(FullReduction fullReduction);
    int updateByPrimaryKeySelective(FullReduction fullReduction);
    List<FullReduction> listFullReductionByShop(@Param(value = "shopId")Long shopId);
    int removeRecord(@Param(value = "activityId")Long activityId);
    FullReduction findSingleFullReductionByShop(@Param(value = "shopId")Long shopId,@Param(value = "amount")Integer amount);
}
