package com.diane.manage.service.impl;

import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.NavInfoMapper;
import com.diane.manage.model.NavInfo;
import com.diane.manage.model.NoticeInfo;
import com.diane.manage.service.NavInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NavInfoServiceImpl implements NavInfoService {
    @Autowired
    private NavInfoMapper navInfoMapper;

    @Override
    public int editNavInfo(NavInfo navInfo) {
        if (navInfo.getId()==null||navInfo.getId()==0){
            navInfo.setFlag(true);
          return  navInfoMapper.insertSelective(navInfo);
        }
        return navInfoMapper.updateByPrimaryKeySelective(navInfo);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,navInfoMapper);
    }

    @Override
    public int deleteNavInfo(List<NavInfo> list) {
        for (NavInfo navInfo:list){
            navInfoMapper.deleteNavInfo(navInfo);
        }
        return 0;
    }

    @Override
    public NoticeInfo getNavByRegion(Long regionId) {
        return null;
    }

    @Override
    public List<NavInfo> listNavByRegion(Long regionId) {
        return navInfoMapper.listNavByRegion(regionId);
    }
}
