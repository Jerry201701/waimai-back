package com.diane.manage.dao;

import com.diane.manage.model.CompanyInfo;
import com.diane.manage.vo.income.CompanyTreeVo;
import com.diane.manage.vo.shop.CompanyBasicInfoVo;
import com.diane.manage.vo.shop.SearchShopVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface CompanyMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CompanyInfo companyInfo);

    int insertSelective(CompanyInfo companyInfo);

    CompanyInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyInfo companyInfo);

    int updateByPrimaryKey(CompanyInfo companyInfo);

    List<CompanyInfo> findPage();
    Page<CompanyInfo> findPageConditions(Map<String,Integer>map);

    List<CompanyInfo> findAll();
    int deleteCompanyInfo(CompanyInfo companyInfo);
    List<CompanyInfo> findPageByUserId(@Param(value = "userId") Long userId);

    Long findUserIdByCompany(@Param(value = "companyId")Long companyId);

    Page<SearchShopVo> searchShopByKeyWord(@Param(value = "keyWord")String key, @Param(value = "regionId") Long regionId);
    Page<SearchShopVo> searchShopByCompany(@Param(value = "regionId")Long regionId,@Param(value = "keyWord")String keyWord);
    Page<SearchShopVo> searchShopByGood(@Param(value = "regionId")Long regionId,@Param(value = "keyWord")String keyWord);
    CompanyBasicInfoVo showCompanyBasicInfo(@Param(value = "companyId")Long companyId);
    List<CompanyTreeVo> listCompanyTree(@Param(value = "regionId")Long regionId);
    int companySettlementWay(@Param(value ="settlementId")Long settlementId,@Param(value ="companyId")Long companyId);

}
