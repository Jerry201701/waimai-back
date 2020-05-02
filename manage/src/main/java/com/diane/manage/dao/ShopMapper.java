package com.diane.manage.dao;


import com.diane.manage.model.CompanyInfo;
import com.diane.manage.model.SettlingModel;
import com.diane.manage.model.ShopInfo;
import com.diane.manage.vo.delivery.DeliveryRuleVo;
import com.diane.manage.vo.income.CompanyTreeVo;
import com.diane.manage.vo.income.ShopTreeVo;
import com.diane.manage.vo.shop.*;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShopMapper {
    int insertSelective(ShopInfo shopInfo);
    List<ShopInfo> findPage();
    List<ShopInfo> findPageByCondition(@Param(value = "shopStatus")Integer shopStatus);
    int updateByPrimaryKeySelective(ShopInfo shopInfo);
    int deleteShopInfo(ShopInfo shopInfo);
    List<ShopInfo> findPageByCompanyId(@Param(value = "companyId") Long companyId);
    List<ShopInfo> listShopByConditions(ShopInfo shopInfo);
    ShopInfo getShopDetails(@Param(value = "shopId") Long shopId);
    ShopInfo getDetailById(@Param(value = "shopId") Long shopId);

    Page<SearchShopVo> sortShopList(@Param(value = "regionId") Long regionId,@Param(value = "type")Integer type,@Param(value = "sort")Boolean sort);
    List<ShopBasicInfoRespVo> shopBasicInfoById(@Param(value = "companyId")Long companyId);
    List<CompanyTreeVo> listShopsByCompany(@Param(value = "companyId")Long companyId);
    int shopDeliverySettlementWay(@Param(value = "settlementId")Long settlementId,@Param(value = "shopId")Long shopId);
    int changeOrderIncome(@Param(value = "income") Integer income,@Param(value = "shopId")Long shopId);
    int addCodeUrl(@Param(value = "codeUrl") String codeUrl,@Param(value = "shopId")Long shopId);
    String findShopCode(@Param(value = "shopId") Long shopId);
    Page<SearchShopVo>searchShopByKeyWord(@Param(value = "regionId")Long regionId,@Param(value = "keyWord")String keyWord);
    Page<RuleShopVo>listShopInfoByRegion(@Param(value = "regionId")Long regionId);
    int addRule(@Param(value = "ruleId")Long ruleId,@Param(value = "shopId")Long shopId);
    List<RuleShopVo> listShopsByRule(@Param(value = "ruleId")Long ruleId);
    DeliveryRuleVo findDeliveryRuleByShop(@Param(value = "shopId")Long shopId);
    ShopBasicInfoRespVo showShopBasicInfo(@Param(value = "shopId")Long companyId);
    SettlingModel findRuleModelByShop(@Param(value = "shopId")Long shopId);
    Integer findPackageFee(@Param(value = "shopId")Long shopId);
    Page<ShopInfo>listShopByPage(QueryShopVo queryShopVo);




}
