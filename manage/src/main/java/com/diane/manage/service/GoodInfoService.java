package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.GoodInfo;
import com.diane.manage.model.ShopInfo;
import com.diane.manage.vo.GoodQuery;
import com.diane.manage.vo.GoodRespVo;
import com.diane.manage.vo.good.GoodQueryVo;
import com.diane.manage.vo.shop.ShopReqVo;
import com.github.pagehelper.Page;

import java.util.List;

public interface GoodInfoService {
    int saveGoodInfo(GoodInfo goodInfo);
    PageResult findPage(PageRequest pageRequest);
    int removeGoodInfo(List<GoodInfo> goodInfos);
    int updateGoodInfo(GoodInfo goodInfo);
    GoodInfo getGoodDetailsById(Long goodId);
    //GoodRespVo getGoodDetailsById(Long goodId);
    List<GoodInfo> listGoodByConditions(GoodQuery goodQuery);
    int copyShopGood(ShopReqVo shopReqVo);
    Page<GoodInfo> goodPageByRegion(GoodQueryVo goodQueryVo);

}
