package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.NoticeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeInfoService {
    int saveNoticeInfo(NoticeInfo noticeInfo);
    int updateNoticeInfo(NoticeInfo noticeInfo);
    PageResult findPage(PageRequest pageRequest);
    int deleteNoticeInfo(List<NoticeInfo> list);
    NoticeInfo getOneNotice(Long regionId);
}
