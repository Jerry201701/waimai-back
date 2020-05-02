package com.diane.manage.service.impl;

import com.diane.common.page.ColumnFilter;
import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.*;
import com.diane.manage.model.CompanyInfo;
import com.diane.manage.model.PictureInfo;
import com.diane.manage.model.SysUserRole;
import com.diane.manage.model.Wallet;
import com.diane.manage.service.CompanyInfoService;
import com.diane.manage.util.HttpUtils;
import com.diane.manage.vo.shop.CompanyBasicInfoVo;
import com.diane.manage.vo.shop.SearchShopVo;
import com.diane.manage.vo.shop.ShopBasicInfoRespVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private DeliveryOrderMapper deliveryOrderMapper;
    @Autowired
    private ShopWithdrawMapper shopWithdrawMapper;
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    @Transactional
    public Long saveCompanyInfo(CompanyInfo record) {
        companyMapper.insertSelective(record);
        /*
        创建钱包
         */
        Wallet wallet=new Wallet();
        wallet.setBalance(0);
        wallet.setCompanyId(record.getId());
        wallet.setCreateTime(new Timestamp(System.currentTimeMillis()));
        wallet.setFlag(true);
        wallet.setRoleType(true);
        walletMapper.addWallet(wallet);
        SysUserRole sysUserRole=new SysUserRole();
        //
        Integer r=15;
        sysUserRole.setRoleId(r.longValue());
        sysUserRole.setUserId(record.getUserId());
        sysUserRoleMapper.insertSelective(sysUserRole);



//        List<PictureInfo> pictureInfos=record.getPictures();
//        if (pictureInfos==null||pictureInfos.size()==0){
//            return 0;
//        }
//        for (PictureInfo pictureInfo:pictureInfos){
//            pictureInfo.setUseId(record.getId());
//            pictureInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
//        }

       // pictureMapper.batchInsert(pictureInfos);

        return record.getId();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        String userId = HttpUtils.getColumnFilterValue(pageRequest, "label");
        if (userId==null){
            return   MybatisPageHelper.findPage(pageRequest, companyMapper);
        }

        // PageResult pageResult=new PageResult();
       // Map<String,Integer> map=new HashMap<>();
        //实际上线要求商户编号id不能为空，否则返回错误
        /*
        if (getColumnFilterValue(pageRequest,"id")==null){
            pageResult.setContent(null);
            return pageResult;
        }
         */
            /*
          ColumnFilter region=pageRequest.getColumnFilter("regionId");
          ColumnFilter user=pageRequest.getColumnFilter("id");
          if (region!=null){
              map.put("regionId",Integer.valueOf(region.getValue()));
              System.out.println(region.getValue());
          }
          if (user!=null){
              map.put("userId",Integer.valueOf(user.getValue()));
              System.out.println(user.getValue());
          }

        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        PageInfo<CompanyInfo> pageInfo=new PageInfo<>(companyMapper.findPageConditions(map));
        pageResult.setContent(pageInfo.getList());
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        */


       return   MybatisPageHelper.findPage(pageRequest, companyMapper, "findPageByUserId", Long.valueOf(userId));
    }

    @Override
    public int updateCompanyInfo(CompanyInfo companyInfo) {
        return companyMapper.updateByPrimaryKeySelective(companyInfo);
    }

    @Override
    public int deleteCompanyInfo(List<CompanyInfo> list) {
        for (CompanyInfo companyInfo :list){
            companyMapper.deleteCompanyInfo(companyInfo);
        }
        return list.size();
    }

    @Override
    public List<CompanyInfo> listCompanyByUser(Long userId) {
        return companyMapper.findPageByUserId(userId);
    }



    @Override
    public Page<SearchShopVo> searchShopByKeyWord(String key, Long regionId) {
        return companyMapper.searchShopByKeyWord(key,regionId);
    }

    @Override
    public Page<SearchShopVo> searchShopByCompany(Long regionId, String keyWord) {
        return companyMapper.searchShopByCompany(regionId,keyWord);
    }

    @Override
    public Page<SearchShopVo> searchShopByGood(Long regionId, String keyWord) {
        return companyMapper.searchShopByGood(regionId,keyWord);
    }

    @Override
    public CompanyBasicInfoVo showCompanyBasicInfo(Long companyId) {
        CompanyBasicInfoVo companyBasicInfoVo=companyMapper.showCompanyBasicInfo(companyId);
        companyBasicInfoVo.setStoreNumber(companyBasicInfoVo.getShops().size());
        Integer balance =0;
        Integer  become=0;
        Integer   orderNumber=0;
        Integer unhandel=0;
         Integer  goodNumber =0;


        for (Long id:companyBasicInfoVo.getShops()){
            ShopBasicInfoRespVo shopBasicInfoRespVo=shopMapper.showShopBasicInfo(id);
           // balance=balance+shopBasicInfoRespVo.getBalance();
            become=become+shopBasicInfoRespVo.getIncome();
            orderNumber=orderNumber+shopBasicInfoRespVo.getOrderNumber();
            unhandel =unhandel+shopBasicInfoRespVo.getUnhandle();
            goodNumber=goodNumber+shopBasicInfoRespVo.getGoodNumber();

        }
        companyBasicInfoVo.setBalance(walletMapper.findCompanyBalance(companyId));
        companyBasicInfoVo.setBecome(become);
        companyBasicInfoVo.setOrderNumber(orderNumber);
        companyBasicInfoVo.setUnhandle(unhandel);
        companyBasicInfoVo.setGoodNumber(goodNumber);

//        List<ShopBasicInfoRespVo> list=shopMapper.shopBasicInfoById(companyId);
//
//        companyBasicInfoVo.setShopBasicInfo(list);
//        Integer balance=0;
//        for (ShopBasicInfoRespVo shopBasicInfoRespVo:list){
//            if (shopBasicInfoRespVo.getShopId()!=null&&deliveryOrderMapper.findOrderIncome(shopBasicInfoRespVo.getShopId())!=null){
//            balance=balance+deliveryOrderMapper.findOrderIncome(shopBasicInfoRespVo.getShopId());
//
//            }
//        }
//        companyBasicInfoVo.setBalance(balance);
//        Integer withdrawAmount=shopWithdrawMapper.findWithdrawAmount(companyId);
//
//        companyBasicInfoVo.setWithdrawAmount(balance-withdrawAmount);

        return companyBasicInfoVo;
    }
}
