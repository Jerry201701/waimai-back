package com.diane.manage.service;

import com.diane.manage.model.WeiUserInfo;

public interface WeiUserInfoService {
    int addUserInfo(WeiUserInfo weiUserInfo);
    WeiUserInfo findUserInfo(String openid);
}
