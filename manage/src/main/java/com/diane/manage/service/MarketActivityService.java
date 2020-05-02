package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.MarketActivity;
import com.diane.manage.vo.activity.ActivityRespVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketActivityService {
    int insertSelective(MarketActivity marketActivity);
    PageResult findPage(PageRequest pageRequest);
    int updateByPrimaryKeySelective(MarketActivity marketActivity);
    int deleteActivityInfo(MarketActivity marketActivity);
    List<ActivityRespVo> findShopByRegion(Long regionId);
    List<ActivityRespVo> findGoodByShop(Long shopId);
    List<ActivityRespVo>  listPromotion(Long regionId);
    ActivityRespVo findPromotionDetail(Long activityId);

}
