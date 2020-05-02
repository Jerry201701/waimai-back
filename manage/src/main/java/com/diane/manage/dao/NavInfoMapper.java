package com.diane.manage.dao;

import com.diane.manage.model.NavInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface NavInfoMapper {
    int insertSelective(NavInfo navInfo);
    List<NavInfo> findPage();
    int updateByPrimaryKeySelective(NavInfo navInfo);
    int deleteNavInfo(NavInfo navInfo);
    List<NavInfo>listNavByRegion(@Param(value = "regionId")Long regionId);
}
