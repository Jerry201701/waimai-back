package com.diane.manage.service.impl;

import com.diane.manage.dao.SubAdminMapper;
import com.diane.manage.model.SubAdmin;
import com.diane.manage.service.SubAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubAdminServiceImpl implements SubAdminService {
    @Autowired
    private SubAdminMapper subAdminMapper;
    @Override
    public int insertSelective(SubAdmin subAdmin) {
        return subAdminMapper.insertSelective(subAdmin);
    }

    @Override
    public int updateByPrimaryKeySelective(SubAdmin subAdmin) {
        return subAdminMapper.updateByPrimaryKeySelective(subAdmin);
    }

    @Override
    public List<SubAdmin> listSubAdmin(Long companyId, Long shopId) {
        return subAdminMapper.listSubAdmin(companyId,shopId);
    }
}
