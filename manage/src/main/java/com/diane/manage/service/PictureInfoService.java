package com.diane.manage.service;

import com.diane.manage.model.PictureInfo;

import java.util.List;

public interface PictureInfoService extends CurdService<PictureInfo> {
   int batchInsert(List<PictureInfo> pictureInfos);
}
