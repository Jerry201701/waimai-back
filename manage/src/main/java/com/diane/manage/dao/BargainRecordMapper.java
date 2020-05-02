package com.diane.manage.dao;

import com.diane.manage.model.BargainRecord;
import com.diane.manage.vo.activity.ActivityConditionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BargainRecordMapper {
  int   addBargainRecord(BargainRecord bargainRecord);
  List<BargainRecord> findBargainRecordById(@Param(value = "bargainId")Long bargainId);
  BargainRecord findLatestRecord(@Param(value = "bargainId") Long bargainId);
  ActivityConditionVo findBargainConditions(@Param(value = "activityId")Long activityId,
                                            @Param(value = "bargainId")Long bargainId);
}
