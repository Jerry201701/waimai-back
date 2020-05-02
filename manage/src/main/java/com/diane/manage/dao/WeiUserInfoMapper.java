package com.diane.manage.dao;

import com.diane.manage.model.WeiUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface WeiUserInfoMapper {
    int addUserInfo(WeiUserInfo weiUserInfo);
    WeiUserInfo findUserInfo(@Param(value = "openid")String openid);
    int deleteUserInfo(@Param(value = "openid")String openid);
}
