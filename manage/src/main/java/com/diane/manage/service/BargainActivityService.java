package com.diane.manage.service;

import com.diane.manage.model.BargainActivity;
import com.diane.manage.vo.activity.BargainActivityRespVo;

import java.util.List;

public interface BargainActivityService {
    Long addBargainActivity(BargainActivity bargainActivity);
    List<BargainActivityRespVo> findBargainActivityByPage(String openid);
}
