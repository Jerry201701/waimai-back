package com.diane.manage.service.impl;

import com.diane.manage.dao.BargainActivityMapper;
import com.diane.manage.dao.BargainRecordMapper;
import com.diane.manage.dao.MarketActivityMapper;
import com.diane.manage.model.BargainActivity;
import com.diane.manage.model.BargainRecord;
import com.diane.manage.service.BargainActivityService;
import com.diane.manage.util.CommonUtil;
import com.diane.manage.vo.activity.ActivityConditionVo;
import com.diane.manage.vo.activity.ActivityRespVo;
import com.diane.manage.vo.activity.BargainActivityRespVo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import java.util.List;

@Service
public class BargainActivityServiceImpl implements BargainActivityService {
    @Autowired
    private BargainActivityMapper bargainMapper;
    @Autowired
    private BargainRecordMapper bargainRecordMapper;
    @Autowired
    private MarketActivityMapper marketActivityMapper;
    @Override
    public Long addBargainActivity(BargainActivity bargainActivity) {
        ActivityRespVo  activityRespVo=marketActivityMapper.findPromotionDetail(bargainActivity.getActivityId());
         bargainActivity.setCreateTime(new Timestamp(System.currentTimeMillis()));
         bargainMapper.addBargainActivity(bargainActivity);
         if (activityRespVo.getBargainSelf()){
             BargainRecord record=new BargainRecord();
             record.setGoodId(bargainActivity.getGoodId());
             record.setOpenid(bargainActivity.getOpenid());
             record.setBargainId(bargainActivity.getId());
             record.setBargainTime(new Timestamp(System.currentTimeMillis()));
             Integer cutOff=CommonUtil.randomInt(activityRespVo.getMinBargainTime(),activityRespVo.getMaxBargainTime());
             Integer currentPrice=(activityRespVo.getPrice()-cutOff);
             record.setBargainPrice(currentPrice);
             record.setCutOff(cutOff);
             bargainRecordMapper.addBargainRecord(record);
         }
         return bargainActivity.getId();
    }

    @Override
    public List<BargainActivityRespVo> findBargainActivityByPage(String openid) {
        List<BargainActivityRespVo>list=bargainMapper.findBargainActivityByPage(openid);
        for(BargainActivityRespVo bargainActivityRespVo:list){
            List<BargainRecord>records =bargainRecordMapper.findBargainRecordById(bargainActivityRespVo.getId());
            if (records==null||records.size()==0){
                bargainActivityRespVo.setCurrentPrice(bargainActivityRespVo.getGoodPrice());
            }
            bargainActivityRespVo.setCurrentPrice(bargainActivityRespVo.getGoodPrice()-records.size()*1);

           // bargainActivityRespVo.getCreateTime()
           // Calendar

          //  Date date = new Date(bargainActivityRespVo.getCreateTime().getTime());
          //  Calendar calendar=Calendar.getInstance();
            Calendar now =Calendar.getInstance();
            Calendar calendar=Calendar.getInstance();
            now.setTimeInMillis(System.currentTimeMillis());
            calendar.setTimeInMillis(bargainActivityRespVo.getCreateTime().getTime());
            Boolean earlier=calendar.before(now);
            calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+bargainActivityRespVo.getValidityTime());
            bargainActivityRespVo.setEndTime(new Timestamp(calendar.getTimeInMillis()));
            Boolean later=now.before(calendar);
            if (earlier&&later){
                bargainActivityRespVo.setStatus(1);
            }
            bargainActivityRespVo.setStatus(2);
         //   after.setTimeInMillis();

//            calendar.setTime(date);
//            Boolean before=calendar.before(now);
//            calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+bargainActivityRespVo.getValidityTime());
//            Timestamp time=new Timestamp(calendar.getTimeInMillis());
//            bargainActivityRespVo.setEndTime(time);



        }
        return list;
    }
}
