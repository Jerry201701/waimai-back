package com.diane.manage.service.impl;

import com.diane.common.page.ColumnFilter;
import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.AdvertisementMapper;
import com.diane.manage.dao.SysUserMapper;
import com.diane.manage.model.Advertisement;
import com.diane.manage.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementMapper advertisementMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public int saveAdvertisement(Advertisement advertisement) {
        return advertisementMapper.insertSelective(advertisement);
    }

    @Override
    public int updateAdvertisement(Advertisement advertisement) {
        return advertisementMapper.updateByPrimaryKeySelective(advertisement);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilter("label");
      //  Long regionId=sysUserMapper.findRegionByUser(Long.valueOf(columnFilter.getValue()));
        return MybatisPageHelper.findPage(pageRequest, advertisementMapper,"findPage",Long.valueOf(columnFilter.getValue()));
    }

    @Override
    public int deleteAdvertisement(List<Advertisement> list) {
        for (Advertisement advertisement :list){
            advertisementMapper.deleteAdvertisement(advertisement);
        }

        return list.size();
    }

    @Override
    public List<Advertisement> listAdvertisement(Long regionId) {
        return advertisementMapper.listAdvertisement(regionId);
    }
}
