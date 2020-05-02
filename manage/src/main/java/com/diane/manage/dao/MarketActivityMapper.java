package com.diane.manage.dao;

import com.diane.manage.model.MarketActivity;
import com.diane.manage.vo.activity.ActivityConditionVo;
import com.diane.manage.vo.activity.ActivityRespVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Mapper
public interface MarketActivityMapper {
    int insertSelective(MarketActivity marketActivity);
    List<MarketActivity> findPage(@Param(value = "regionId") Long regionId);
    int updateByPrimaryKeySelective(MarketActivity marketActivity);
    int deleteActivityInfo(MarketActivity marketActivity);
    List<ActivityRespVo> findShopByRegion(@Param(value = "regionId")Long regionId);
    List<ActivityRespVo> findGoodByShop(@Param(value  ="shopId")Long shopId);
    List<ActivityRespVo>  listPromotion(@Param(value = "regionId")Long regionId);
    ActivityRespVo findPromotionDetail(@Param(value = "activityId")Long activityId);
    ActivityConditionVo findActivityCondition(@Param(value = "activityId")Long activityId);


  //  List<NavInfo>listNavByRegion(@Param(value = "regionId")Long regionId);

}
