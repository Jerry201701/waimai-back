package com.diane.manage.service.impl;

import com.diane.common.page.ColumnFilter;
import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.MarketActivityMapper;
import com.diane.manage.model.MarketActivity;
import com.diane.manage.service.MarketActivityService;
import com.diane.manage.vo.activity.ActivityRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketActivityServiceImpl implements MarketActivityService {
    @Autowired
    private MarketActivityMapper marketActivityMapper;
    @Override
    public int insertSelective(MarketActivity marketActivity) {
        return marketActivityMapper.insertSelective(marketActivity);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilter("label");

        return MybatisPageHelper.findPage(pageRequest, marketActivityMapper,"findPage",Long.valueOf(columnFilter.getValue()));
    }


    @Override
    public int updateByPrimaryKeySelective(MarketActivity marketActivity) {
        return marketActivityMapper.updateByPrimaryKeySelective(marketActivity);
    }

    @Override
    public int deleteActivityInfo(MarketActivity marketActivity) {
        return marketActivityMapper.deleteActivityInfo(marketActivity);
    }

    @Override
    public List<ActivityRespVo> findShopByRegion(Long regionId) {
        return marketActivityMapper.findShopByRegion(regionId);
    }

    @Override
    public List<ActivityRespVo> findGoodByShop(Long shopId) {
        return marketActivityMapper.findGoodByShop(shopId);
    }


    @Override
    public List<ActivityRespVo> listPromotion(Long regionId) {

        return marketActivityMapper.listPromotion(regionId);
    }

    @Override
    public ActivityRespVo findPromotionDetail(Long activityId) {
        return marketActivityMapper.findPromotionDetail(activityId);
    }
}
