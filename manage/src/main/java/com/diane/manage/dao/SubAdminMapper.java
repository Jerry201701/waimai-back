package com.diane.manage.dao;

import com.diane.manage.model.SubAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SubAdminMapper {
    int insertSelective(SubAdmin subAdmin);
    int updateByPrimaryKeySelective(SubAdmin subAdmin);
    List<SubAdmin> listSubAdmin(@Param(value = "companyId")Long companyId,@Param(value = "shopId")Long shopId);
    Long findUserIdByShop(@Param(value = "shopId")Long shopId);
}
