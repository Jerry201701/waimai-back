package com.diane.manage.service.impl;

import com.diane.manage.dao.BargainActivityMapper;
import com.diane.manage.dao.BargainRecordMapper;
import com.diane.manage.dao.MarketActivityMapper;
import com.diane.manage.model.BargainRecord;
import com.diane.manage.service.BargainRecordService;
import com.diane.manage.util.CommonUtil;
import com.diane.manage.vo.activity.ActivityConditionVo;
import com.diane.manage.vo.activity.ActivityRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Service
public class BargainRecordServiceImpl implements BargainRecordService {
    @Autowired
    private BargainRecordMapper bargainRecordMapper;
    @Autowired
    private BargainActivityMapper bargainActivityMapper;

    @Override
    public Integer addBargainRecord(BargainRecord bargainRecord) {
        List<String> openid=bargainActivityMapper.findOpenid(bargainRecord.getBargainId());
        //ActivityRespVo activityRespVo=marketActivityMapper.findPromotionDetail(bargainRecord.getActivityId());

        /*
        砍过的不能砍
         */
        if (openid.contains(bargainRecord.getOpenid())){
            return 2;
        }


        ActivityConditionVo activityConditionVo=bargainRecordMapper.findBargainConditions(bargainRecord.getActivityId(),bargainRecord.getBargainId());
        /*
        超过砍价次数不能砍了
         */
        if (activityConditionVo.getBargainTimeLimit()==activityConditionVo.getBargainTimes()){
            return 4;
        }
        /*
        超过有效期了
         */
        //   Long length=activityConditionVo.getBargainTime().getTime();
        //  length.
        Calendar now =Calendar.getInstance();
        Calendar calendar=Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        calendar.setTimeInMillis(activityConditionVo.getBargainTime().getTime());
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+activityConditionVo.getBargainValidity());
        if (calendar.before(now)){
            return 5;
        }




        /*
        生成砍价价格，不能超过单次砍价金额限制

         */

        Integer bargain= CommonUtil.randomInt(activityConditionVo.getMinBargainLimit(),activityConditionVo.getBargainLimit());

        /*
        超过砍价允许最低价格了不能砍了
         */
        if (activityConditionVo.getBargainCurrentPrice()-bargain<activityConditionVo.getMinBargainPayment()){
            return 3;
        }


        bargainRecord.setBargainTime(new Timestamp(System.currentTimeMillis()));
        BargainRecord record=bargainRecordMapper.findLatestRecord(bargainRecord.getBargainId());
        Integer bargainPrice=null;
        if (record.getBargainPrice()==null){
            bargainPrice=record.getGoodPrice()-bargain;
        }else {
        bargainPrice=record.getBargainPrice()-bargain;
        }
        bargainRecord.setBargainPrice(bargainPrice);
        bargainRecord.setCutOff(bargain);
      //  List<BargainRecord> list=bargainRecordMapper.findBargainRecordById(bargainRecord.getBargainId());
        int i=bargainRecordMapper.addBargainRecord(bargainRecord);
        if (i==1){
            return bargain;
        }
        return 0;
    }

    @Override
    public List<BargainRecord> findBargainRecordById(Long bargainId) {
        return bargainRecordMapper.findBargainRecordById(bargainId);
    }
}
