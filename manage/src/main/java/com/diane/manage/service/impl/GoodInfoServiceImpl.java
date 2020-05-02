package com.diane.manage.service.impl;

import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.*;
import com.diane.manage.model.*;
import com.diane.manage.service.GoodInfoService;
import com.diane.manage.vo.GoodQuery;
import com.diane.manage.vo.GoodRespVo;
import com.diane.manage.vo.category.CopyCategoryVo;
import com.diane.manage.vo.good.CopyGoodVo;
import com.diane.manage.vo.good.GoodQueryVo;
import com.diane.manage.vo.shop.ShopReqVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class GoodInfoServiceImpl implements GoodInfoService {
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private GoodLabelMapper goodLabelMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private CategoryInfoMapper categoryInfoMapper;
    @Override
    @Transactional
    public int saveGoodInfo(GoodInfo goodInfo) {
            goodInfo.setFlag(false);
            goodInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
            int i=goodMapper.insertSelective(goodInfo);
            ThreadPoolExecutor executor=new ThreadPoolExecutor(2,3,1,
                    TimeUnit.SECONDS,new LinkedBlockingDeque<>());
            CountDownLatch latch=new CountDownLatch(2);
            executor.execute(()->{
                try {
                    if (goodInfo.getLabels()!=null&&goodInfo.getLabels().size()!=0) {
                        for (GoodLabel goodLabel : goodInfo.getLabels()) {
                            goodLabel.setGoodId(goodInfo.getId());
                            goodLabel.setFlag(false);
                            goodLabelMapper.insertSelective(goodLabel);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });

            executor.execute(()->{
                try {
                    if (goodInfo.getGoodPics()!=null&&goodInfo.getGoodPics().size()!=0){
                        for (PictureInfo pictureInfo : goodInfo.getGoodPics()){
                            pictureInfo.setGoodId(goodInfo.getId());
                            pictureInfo.setFlag(false);
                            pictureInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                            pictureMapper.insertSelective(pictureInfo);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;

    }
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, goodMapper);
    }

    @Override
    public int removeGoodInfo(List<GoodInfo> goodInfos) {
        for (int i = 0; i < goodInfos.size(); i++) {
             goodMapper.deleteGoodInfo(goodInfos.get(i));
        }
        return goodInfos.size();
    }

    @Override
    @Transactional
    public int updateGoodInfo(GoodInfo goodInfo) {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(2,3,1,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        CountDownLatch latch=new CountDownLatch(2);
        executor.execute(()->{
            try {
                if (goodInfo.getGoodPics()!=null &&goodInfo.getGoodPics().size()>0) {
                    pictureMapper.movePictureByGood(goodInfo.getId());
                    for (PictureInfo pictureInfo : goodInfo.getGoodPics()) {
                        pictureInfo.setGoodId(goodInfo.getId());
                        pictureInfo.setFlag(false);
                        pictureInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        pictureMapper.insertSelective(pictureInfo);

//                        if (pictureInfo.getId()==null||pictureInfo.getId()==0){
//                        pictureInfo.setGoodId(goodInfo.getId());
//                        pictureMapper.insertSelective(pictureInfo);
//                        }
//                        pictureMapper.updateByPrimaryKeySelective(pictureInfo);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            latch.countDown();
        });
        executor.execute(()->{
            try {
                if (goodInfo.getLabels()!=null) {
                    for (GoodLabel goodLabel : goodInfo.getLabels()) {
                        if (goodInfo.getId()==null||goodInfo.getId()==0){
                        goodLabel.setGoodId(goodInfo.getId());
                        goodLabelMapper.insertSelective(goodLabel);
                        }
                        goodLabelMapper.updateByPrimaryKeySelective(goodLabel);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            latch.countDown();
        });

        int i=goodMapper.updateByPrimaryKeySelective(goodInfo);
        return i;
    }

    @Override
    public GoodInfo getGoodDetailsById(Long goodId) {
        return goodMapper.getGoodDetailsById(goodId);
    }



//
//    @Override
//    public GoodRespVo getGoodDetailsById(Long goodId) {
//        return goodMapper.getGoodDetailsById(goodId);
//    }

    @Override
    public List<GoodInfo> listGoodByConditions(GoodQuery goodQuery) {
        return goodMapper.listGoodByConditions(goodQuery);
    }

    @Override
    public int copyShopGood(ShopReqVo shopReqVo) {
        shopReqVo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        categoryInfoMapper.copyGoodCategory(shopReqVo);
        int i= goodMapper.copyShopGood(shopReqVo);
        List<CopyCategoryVo> categorys=categoryInfoMapper.listSourceByShop(shopReqVo.getShopId());
        for (CopyCategoryVo copyCategoryVo:categorys){
            goodMapper.changeCopyCategoryId(copyCategoryVo);
        }

        List<CopyGoodVo> goods=goodMapper.findGoodPicInfoByShop(shopReqVo.getShopId());
        for (CopyGoodVo copyGoodVo:goods){
            copyGoodVo.setFlag(false);
            copyGoodVo.setCreateTime(new Timestamp(System.currentTimeMillis()));
            pictureMapper.copyGoodPicture(copyGoodVo);
            goodLabelMapper.copyGoodLabel(copyGoodVo);

        }

//        List<PictureInfo> pictures=goodMapper.findGoodPicInfoByShop(shopReqVo.getConeShopId());
//        for (PictureInfo pictureInfo : pictures){
//            pictureInfo.setFlag(false);
//            pictureInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
//            pictureMapper.insertSelective(pictureInfo);
//        }
        return i;
    }

    @Override
    public Page<GoodInfo> goodPageByRegion(GoodQueryVo goodQueryVo) {
        PageHelper.startPage(goodQueryVo.getPageNum(),goodQueryVo.getPageSize());
        return goodMapper.goodPageByRegion(goodQueryVo);
    }
}
