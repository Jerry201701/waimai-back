package com.diane.manage.service;

import com.diane.manage.model.SubAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubAdminService {
    int insertSelective(SubAdmin subAdmin);
    int updateByPrimaryKeySelective(SubAdmin subAdmin);
    List<SubAdmin> listSubAdmin(Long companyId,Long shopId);
}
