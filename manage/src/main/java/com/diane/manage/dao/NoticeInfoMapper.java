package com.diane.manage.dao;

import com.diane.manage.model.NoticeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface NoticeInfoMapper {
    int insertSelective(NoticeInfo notice);
    List<NoticeInfo> findPage();
    int updateByPrimaryKeySelective(NoticeInfo noticeInfo);
    int deleteNoticeInfo(NoticeInfo noticeInfo);
    List<NoticeInfo> findPageByRegion(@Param(value = "regionId")Long regionId);
    NoticeInfo getOneNotice(@Param(value = "regionId")Long regionId);

}
