package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.NavInfo;
import com.diane.manage.model.NoticeInfo;

import java.util.List;

public interface NavInfoService {
    int editNavInfo(NavInfo navInfo);
    PageResult findPage(PageRequest pageRequest);
    int deleteNavInfo(List<NavInfo> list);
    NoticeInfo getNavByRegion(Long regionId);
    List<NavInfo>listNavByRegion(Long regionId);


}
