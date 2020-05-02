package com.diane.manage.service.impl;

import com.diane.common.page.ColumnFilter;
import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.*;
import com.diane.manage.model.*;
import com.diane.manage.service.ShopService;
import com.diane.manage.util.CosUploadUtil;
import com.diane.manage.util.HttpUtils;
import com.diane.manage.vo.delivery.DeliveryRuleVo;
import com.diane.manage.vo.shop.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Service
public class ShopInfoServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private ShopCategoryMapper shopCategoryMapper;
    @Autowired
    private DeliveryCommentMapper deliveryCommentMapper;
    @Autowired
    private ShopCollectionMapper shopCollectionMapper;
    @Autowired WalletMapper walletMapper;
    @Override
    @Transactional
    public int saveShopInfo(ShopInfo shopInfo) {
        shopInfo.setFlag(false);
        shopInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        int i=shopMapper.insertSelective(shopInfo);

        if (shopInfo.getPictures()==null||shopInfo.getPictures().size()==0){
            if (shopInfo.getCategoryList()!=null && shopInfo.getCategoryList().size()!=0) {
                for (CategoryInfo categoryInfo : shopInfo.getCategoryList()) {
                    ShopCategory shopCategory = new ShopCategory();
                    shopCategory.setShopId(shopInfo.getId());
                    shopCategory.setCategoryId(categoryInfo.getId());
                    shopCategoryMapper.insertSelective(shopCategory);
                }
            }
        if (shopInfo.getPictureInfoList()!=null&&shopInfo.getPictureInfoList().size()!=0){

        for (PictureInfo pictureInfo : shopInfo.getPictureInfoList()){
            pictureInfo.setShopId(shopInfo.getId());
            pictureInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
            pictureMapper.insertSelective(pictureInfo);
        }
        }
      //  String codeUrl=CosUploadUtil.saveCode("30_fIrMG-w0xy1q6l6WZg95bhMOy7exhF6Sx3ERxnd5e4BsbfoX0dXCT5M4zGTTwYdR47uMLJ-27bUBUXjFi1Fk30WJJobNoCwA10ARse8Ti6WyBA0E7mcYdEON53QRWNcAAAVAR",shopInfo.getId().toString());
      //  shopMapper.addCodeUrl(codeUrl,shopInfo.getId());

//        List<PictureInfo> pictureInfos=shopInfo.getPictures();
//
//        Iterator<PictureInfo> ite = pictureInfos.iterator();
//        if(ite==null){
//            System.out.println("没有图片");
//            return 0;
//        }
//        while (ite.hasNext()) {
//            ite.next().setUseId(shopInfo.getId());
//        }
//        pictureMapper.batchInsert(pictureInfos);
//
//        return i;
//    }
            return  i;
        }
            return 0;
    }

    @Override
    public int updateShopInfo(ShopInfo shopInfo) {

        if (shopInfo.getCategoryList()!=null && shopInfo.getCategoryList().size()!=0) {
            for (CategoryInfo categoryInfo : shopInfo.getCategoryList()) {
                ShopCategory shopCategory = new ShopCategory();
                shopCategory.setShopId(shopInfo.getId());
                shopCategory.setCategoryId(categoryInfo.getId());
                shopCategoryMapper.updateByPrimaryKeySelective(shopCategory);
            }
        }
        if (shopInfo.getPictureInfoList()!=null&&shopInfo.getPictureInfoList().size()!=0){

            for (PictureInfo pictureInfo : shopInfo.getPictureInfoList()){
                pictureInfo.setShopId(shopInfo.getId());
                pictureInfo.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
                pictureMapper.updateByPrimaryKeySelective(pictureInfo);
            }
        }

        return shopMapper.updateByPrimaryKeySelective(shopInfo);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilter("label");
        String status=columnFilter.getValue();
        if (status.isEmpty()){
        return   MybatisPageHelper.findPage(pageRequest, shopMapper);
        }
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        Integer shopStatus=Integer.valueOf(columnFilter.getValue());
        return MybatisPageHelper.findPage(pageRequest,shopMapper,"findPageByCondition",shopStatus);



    }

    @Override
    public int deleteShopInfo(List<ShopInfo> list) {
        for (ShopInfo shopInfo :list){
            shopMapper.deleteShopInfo(shopInfo);
        }
        return list.size();
    }

    @Override
    public PageResult findPageByCompany(PageRequest pageRequest) {
        PageResult pageResult=new PageResult();
        String companyId = HttpUtils.getColumnFilterValue(pageRequest, "label");
        if (companyId==null||companyId.isEmpty()){
            pageResult.setContent(null);
            return pageResult;
        }
        return   MybatisPageHelper.findPage(pageRequest, shopMapper, "findPageByCompanyId", Long.valueOf(companyId));
    }

    @Override
    public List<ShopInfo> listShopByConditions(ShopInfo shopInfo) {
        if (shopInfo.getCompanyId()==null){
            return null;
        }
        List<ShopInfo> list= shopMapper.listShopByConditions(shopInfo);
        return list;
    }

    @Override
    public ShopInfo getShopDetails(Long shopId) {
        ShopInfo shopInfo=shopMapper.getDetailById(shopId);
        ThreadPoolExecutor executor=new ThreadPoolExecutor(3,4,1,
        TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        CountDownLatch latch=new CountDownLatch(3);
        executor.execute(()-> {

            try {
                shopInfo.setCommentList(deliveryCommentMapper.listCommentDetailByShop(shopId));
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
            latch.countDown();
            }
        });
         executor.execute(()-> {
             try {
                 shopInfo.setPictureInfoList(pictureMapper.listPictureByShop(shopId));
             } catch (Exception e) {
                 e.printStackTrace();
             }finally {
             latch.countDown();
             }
        });
         executor.execute(()->{
             try {
                 shopInfo.setCategoryList(shopCategoryMapper.listCategoryByShop(shopId));
             } catch (Exception e) {
                 e.printStackTrace();
             }finally {
             latch.countDown();
             }
         });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return shopInfo;
    }

    @Override
    public int deleteSingleShopInfo(ShopInfo shopInfo) {
        return shopMapper.deleteShopInfo(shopInfo);
    }


    @Override
    public Page<SearchShopVo> sortShopList(Long regionId, Integer type, Boolean sort) {
        return shopMapper.sortShopList(regionId,type,sort);
    }

    @Override
    public ShopInfo getShopDetailByOpenid(Long shopId, String openid) {
        ShopInfo shopInfo=shopMapper.getDetailById(shopId);
        ThreadPoolExecutor executor=new ThreadPoolExecutor(4,5,1,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        CountDownLatch latch=new CountDownLatch(4);
        executor.execute(()-> {

            try {
                List<DeliveryComment>list=deliveryCommentMapper.listCommentDetailByShop(shopId);
                if (list !=null &&list.size()>0){
                shopInfo.setCommentList(list);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                latch.countDown();
            }
        });
        executor.execute(()-> {
            try {
                List<PictureInfo> pictureInfos =pictureMapper.listPictureByShop(shopId);
                if (pictureInfos !=null&&pictureInfos.size()>0){
                shopInfo.setPictureInfoList(pictureInfos);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                latch.countDown();
            }
        });
        executor.execute(()->{
            try {
                shopInfo.setCategoryList(shopCategoryMapper.listCategoryByShop(shopId));
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                latch.countDown();
            }
        });
        executor.execute(()->{
            try {
                shopInfo.setShopFav(shopCollectionMapper.findCollectionStatus(openid,shopId));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        });


        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return shopInfo;
    }

    @Override
    public String findShopCode(Long shopId) {
        return shopMapper.findShopCode(shopId);
    }

    @Override
    public Page<SearchShopVo> searchShopByKeyWord(Long regionId, String keyWord) {
        return shopMapper.searchShopByKeyWord(regionId,keyWord);
    }

    @Override
    public Page<RuleShopVo> listShopInfoByRegion(Long regionId) {
        return shopMapper.listShopInfoByRegion(regionId);
    }

    @Override
    public int addRule(Long ruleId, Long shopId) {
        return shopMapper.addRule(ruleId,shopId);
    }

    @Override
    public List<RuleShopVo> listShopsByRule(Long ruleId) {
        return shopMapper.listShopsByRule(ruleId);
    }

    @Override
    public DeliveryRuleVo findDeliveryRuleByShop(Long shopId) {
        return shopMapper.findDeliveryRuleByShop(shopId);
    }

    @Override
    public ShopBasicInfoRespVo showShopBasicInfo(Long shopId) {

        return shopMapper.showShopBasicInfo(shopId);
    }

    @Override
    public Integer findPackageFee(Long shopId) {
        return shopMapper.findPackageFee(shopId);
    }

    @Override
    public Page<ShopInfo> listShopByPage(QueryShopVo queryShopVo) {
        return shopMapper.listShopByPage(queryShopVo);
    }
}
