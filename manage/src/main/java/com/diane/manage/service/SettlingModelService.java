package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.SettlingModel;
import com.diane.manage.vo.income.CompanyTreeVo;
import com.diane.manage.vo.income.GoodTreeVo;
import com.diane.manage.vo.income.ShopTreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SettlingModelService {
    int insertSelective(SettlingModel settlingModel);
    int updateByPrimaryKeySelective(SettlingModel settlingModel);
    PageResult findPageByRegion(PageRequest pageRequest);
    List<CompanyTreeVo> listCompanyTree(Long regionId);
    List<CompanyTreeVo> listShopsByCompany(Long companyId);
    List<CompanyTreeVo> listGoodsByShop(Long shopId);
    List<String> findCheck(Long settlementId);
    int removeSettlementRecord(Long settlementId);
    SettlingModel findSettlementByShop(Long shopId);
    Integer  computeDeliveryFee(Long shopId,Integer amount);
}
