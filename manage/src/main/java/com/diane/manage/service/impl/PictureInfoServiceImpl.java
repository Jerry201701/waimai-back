package com.diane.manage.service.impl;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.PictureMapper;
import com.diane.manage.model.PictureInfo;
import com.diane.manage.service.PictureInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PictureInfoServiceImpl implements PictureInfoService {
    @Autowired
    private PictureMapper pictureMapper;
    @Override
    public int save(PictureInfo record) {
        return 0;
    }

    @Override
    public int delete(PictureInfo record) {
        return 0;
    }

    @Override
    public int delete(List<PictureInfo> records) {
        return 0;
    }

    @Override
    public PictureInfo findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    public int batchInsert(List<PictureInfo> pictureInfos) {
        return pictureMapper.batchInsert(pictureInfos);
    }
}
