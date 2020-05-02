package com.diane.manage.service.impl;

import com.diane.common.page.ColumnFilter;
import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.NoticeInfoMapper;
import com.diane.manage.model.NoticeInfo;
import com.diane.manage.service.NoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoticeInfoServiceImpl implements NoticeInfoService {
    @Autowired
    private NoticeInfoMapper noticeInfoMapper;

    @Override
    public int saveNoticeInfo(NoticeInfo noticeInfo) {
        return noticeInfoMapper.insertSelective(noticeInfo);
    }

    @Override
    public int updateNoticeInfo(NoticeInfo noticeInfo) {
        return noticeInfoMapper.updateByPrimaryKeySelective(noticeInfo);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilter("label");

        return MybatisPageHelper.findPage(pageRequest,noticeInfoMapper,"findPageByRegion",Long.valueOf(columnFilter.getValue()));
    }

    @Override
    public int deleteNoticeInfo(List<NoticeInfo> list) {
        for (NoticeInfo noticeInfo :list){
            noticeInfoMapper.deleteNoticeInfo(noticeInfo);
        }
        return list.size();
    }


    @Override
    public NoticeInfo getOneNotice(Long regionId) {
        return noticeInfoMapper.getOneNotice(regionId);
    }
}
