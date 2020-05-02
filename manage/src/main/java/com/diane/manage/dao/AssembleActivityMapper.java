package com.diane.manage.dao;

import com.diane.manage.model.AssembleActivity;
import com.diane.manage.vo.activity.AssembleActivityRespVo;
import com.diane.manage.vo.activity.CreateAssembleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AssembleActivityMapper {
   int addAssembleActivity(AssembleActivity assembleActivity);
   List<String> findLeaderOpenid(@Param(value = "assembleId")Long assembleId);
   AssembleActivityRespVo findAssembleDetail(@Param(value = "assembleId")Long assembleId);
   List<String> findHeadIcon(@Param(value = "assembleId")Long assembleId);
   Integer countAssembleNumber(@Param(value = "assembleId")Long assembleId);
   List<CreateAssembleVo> listAssembleActivity(@Param(value = "openid")String openid);
}
