package com.diane.manage.dao;

import com.diane.manage.model.SysRoleDept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface SysRoleDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleDept record);

    int insertSelective(SysRoleDept record);

    SysRoleDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleDept record);

    int updateByPrimaryKey(SysRoleDept record);
}