package com.diane.manage.dao;

import com.diane.manage.model.BargainActivity;
import com.diane.manage.vo.activity.BargainActivityRespVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BargainActivityMapper {
   int addBargainActivity(BargainActivity bargainActivity);
   int changeBargainPrice(@Param(value = "bargainId")Long bargainId,@Param(value = "currentPrice") Integer currentPrice);
   List<BargainActivityRespVo> findBargainActivityByPage(@Param(value = "openid")String openid);
   BargainActivityRespVo findBargainById(@Param(value = "bargainId")Long bargainId);
   List<String> findOpenid(@Param(value = "bargainId")Long bargainId);
}
