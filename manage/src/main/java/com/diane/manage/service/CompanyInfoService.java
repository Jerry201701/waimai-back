package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.CompanyInfo;
import com.diane.manage.vo.shop.CompanyBasicInfoVo;
import com.diane.manage.vo.shop.SearchShopVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyInfoService {
    Long saveCompanyInfo(CompanyInfo companyInfo);
    int updateCompanyInfo(CompanyInfo companyInfo);
    PageResult findPage(PageRequest pageRequest);
    int deleteCompanyInfo(List<CompanyInfo> list);
    List<CompanyInfo> listCompanyByUser(Long userId);
    Page<SearchShopVo> searchShopByKeyWord(String key, Long regionId);
    Page<SearchShopVo> searchShopByCompany(Long regionId, String keyWord);
    Page<SearchShopVo> searchShopByGood(Long regionId,String keyWord);
    CompanyBasicInfoVo showCompanyBasicInfo(Long companyId);


}
