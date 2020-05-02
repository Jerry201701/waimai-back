package com.diane.manage.service;

import com.diane.manage.model.AssembleActivity;
import com.diane.manage.vo.activity.AssembleActivityRespVo;
import com.diane.manage.vo.activity.CreateAssembleVo;

import java.util.List;

public interface AssembleActivityService {
    Long addAssembleActivity(AssembleActivity assembleActivity);
    AssembleActivityRespVo findAssembleDetail(Long assembleId);
    List<CreateAssembleVo>listCreateAssemble(String openid);
}
