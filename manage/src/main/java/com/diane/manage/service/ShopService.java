package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.ShopInfo;
import com.diane.manage.vo.delivery.DeliveryRuleVo;
import com.diane.manage.vo.shop.*;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopService {
    int saveShopInfo(ShopInfo shopInfo);
    int updateShopInfo(ShopInfo shopInfo);
    PageResult findPage(PageRequest pageRequest);
    int deleteShopInfo(List<ShopInfo> list);
    PageResult findPageByCompany(PageRequest pageRequest);
    List<ShopInfo> listShopByConditions(ShopInfo shopInfo);
    ShopInfo getShopDetails(Long shopId);
    ShopInfo getShopDetailByOpenid(Long shopId,String openid);
    int deleteSingleShopInfo(ShopInfo shopInfo);
    String findShopCode(Long shopId);

    Page<SearchShopVo> sortShopList(Long regionId,Integer type,Boolean sort);

    Page<SearchShopVo>searchShopByKeyWord(Long regionId,String keyWord);

    Page<RuleShopVo>listShopInfoByRegion(Long regionId);
    int addRule(Long ruleId,Long shopId);
    List<RuleShopVo> listShopsByRule(Long ruleId);
    DeliveryRuleVo findDeliveryRuleByShop(Long shopId);
    ShopBasicInfoRespVo showShopBasicInfo(Long shopId);
    Integer findPackageFee(Long shopId);

    Page<ShopInfo> listShopByPage(QueryShopVo queryShopVo);


}
