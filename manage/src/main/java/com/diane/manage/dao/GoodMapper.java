package com.diane.manage.dao;

import com.diane.manage.model.GoodInfo;
import com.diane.manage.model.PictureInfo;
import com.diane.manage.model.ShopInfo;
import com.diane.manage.vo.GoodQuery;
import com.diane.manage.vo.GoodRespVo;
import com.diane.manage.vo.category.CopyCategoryVo;
import com.diane.manage.vo.good.CopyGoodVo;
import com.diane.manage.vo.good.GoodQueryVo;
import com.diane.manage.vo.income.CompanyTreeVo;
import com.diane.manage.vo.income.GoodTreeVo;
import com.diane.manage.vo.shop.ShopReqVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface GoodMapper {

    int insertSelective(GoodInfo goodInfo);
    List<GoodInfo> findPage();
    int updateByPrimaryKeySelective(GoodInfo goodInfo);
    int deleteGoodInfo(GoodInfo goodInfo);
    GoodInfo getGoodDetailsById(@Param(value = "goodId")Long goodId);
    //GoodRespVo getGoodDetailsById(@Param(value = "goodId")Long goodId);
    List<GoodInfo> listGoodByConditions(GoodQuery goodQuery);
    int copyShopGood(ShopReqVo shopReqVo);
    List<CopyGoodVo> findGoodPicInfoByShop(@Param(value = "shopId")Long shopId);
    List<CompanyTreeVo> findGoodByShop(@Param(value = "shopId")Long shopId);
    int goodDeliverySettlementWay(@Param(value = "settlementId")Long settlementId,@Param(value = "goodId")Long goodId);
    int changeCopyCategoryId(CopyCategoryVo copyCategoryVo);
    Page<GoodInfo> goodPageByRegion(GoodQueryVo goodQueryVo);
    Integer findShopStock(@Param(value = "shopId")Long shopId);
    int updateStock(@Param(value = "quantity")Integer quantity,@Param(value = "shopId")Long shopId);

}
