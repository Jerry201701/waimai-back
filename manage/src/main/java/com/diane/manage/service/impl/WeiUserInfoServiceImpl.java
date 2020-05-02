package com.diane.manage.service.impl;

import com.diane.manage.dao.WeiUserInfoMapper;
import com.diane.manage.model.WeiUserInfo;
import com.diane.manage.service.WeiUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class WeiUserInfoServiceImpl implements WeiUserInfoService {
    @Autowired
    private WeiUserInfoMapper weiUserInfoMapper;
    @Override
    public int addUserInfo(WeiUserInfo weiUserInfo) {
        weiUserInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        WeiUserInfo userInfo=weiUserInfoMapper.findUserInfo(weiUserInfo.getOpenid());
        if (userInfo!=null){
        weiUserInfoMapper.deleteUserInfo(weiUserInfo.getOpenid());
        }
        return weiUserInfoMapper.addUserInfo(weiUserInfo);
    }

    @Override
    public WeiUserInfo findUserInfo(String openid) {
        return weiUserInfoMapper.findUserInfo(openid);
    }
}
