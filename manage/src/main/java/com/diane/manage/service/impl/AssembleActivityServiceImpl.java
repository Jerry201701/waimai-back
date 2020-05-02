package com.diane.manage.service.impl;

import com.diane.common.http.HttpResult;
import com.diane.manage.dao.AssembleActivityMapper;
import com.diane.manage.dao.MarketActivityMapper;
import com.diane.manage.model.AssembleActivity;
import com.diane.manage.service.AssembleActivityService;
import com.diane.manage.vo.activity.ActivityConditionVo;
import com.diane.manage.vo.activity.AssembleActivityRespVo;
import com.diane.manage.vo.activity.CreateAssembleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class AssembleActivityServiceImpl implements AssembleActivityService {
    @Autowired
    private AssembleActivityMapper assembleActivityMapper;
    @Autowired
    private  MarketActivityMapper marketActivityMapper;
    @Override
    public Long addAssembleActivity(AssembleActivity assembleActivity) {
        assembleActivity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ActivityConditionVo activityCondition =marketActivityMapper.findActivityCondition(assembleActivity.getActivityId());
        if (assembleActivity.getAssembleId()==null){
        assembleActivity.setAssemblePrice(activityCondition.getLeaderPrice());
        assembleActivity.setLeader(true);
        assembleActivityMapper.addAssembleActivity(assembleActivity);
        return assembleActivity.getAssembleId();
        }
        List<String> ids=assembleActivityMapper.findLeaderOpenid(assembleActivity.getAssembleId());
        if (ids.contains(assembleActivity.getOpenid())){
            return null;
        }

        assembleActivity.setAssemblePrice(activityCondition.getMemberPrice());
        assembleActivity.setSuperiorId(assembleActivity.getAssembleId());
        assembleActivity.setAssembleId(null);
        assembleActivityMapper.addAssembleActivity(assembleActivity);
        return assembleActivity.getAssembleId();


    }

    @Override
    public AssembleActivityRespVo findAssembleDetail(Long assembleId) {
        AssembleActivityRespVo assembleActivityRespVo=assembleActivityMapper.findAssembleDetail(assembleId);
        assembleActivityRespVo.setUserPicUrl(assembleActivityMapper.findHeadIcon(assembleId));
        assembleActivityRespVo.setAssembleId(assembleId);
        assembleActivityRespVo.setLeftCount(0);
        if (assembleActivityRespVo.getAssembleTimes()<assembleActivityRespVo.getMinPeople()){
            assembleActivityRespVo.setLeftCount(assembleActivityRespVo.getMinPeople()-assembleActivityRespVo.getAssembleTimes());
        }
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(assembleActivityRespVo.getStartTime().getTime());
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+assembleActivityRespVo.getGroupValidityTime());
        assembleActivityRespVo.setEndTime(new Timestamp(calendar.getTimeInMillis()));

        return assembleActivityRespVo;
    }

    @Override
    public List<CreateAssembleVo> listCreateAssemble(String openid) {
        List<CreateAssembleVo>list=assembleActivityMapper.listAssembleActivity(openid);
        for (CreateAssembleVo createAssembleVo:list){
            Integer total=assembleActivityMapper.countAssembleNumber(createAssembleVo.getAssembleId());
            createAssembleVo.setLeftCount(createAssembleVo.getMinPeople()-total);
            Calendar calendar=Calendar.getInstance();
            calendar.setTimeInMillis(createAssembleVo.getStartTime().getTime());
            calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+createAssembleVo.getGroupValidityTime());
            createAssembleVo.setEndTime(new Timestamp(calendar.getTimeInMillis()));

        }
        return list;
    }
}
