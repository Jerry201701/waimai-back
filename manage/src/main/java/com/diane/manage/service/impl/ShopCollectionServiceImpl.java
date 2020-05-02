package com.diane.manage.service.impl;

import com.diane.manage.dao.ShopCollectionMapper;
import com.diane.manage.model.ShopCollection;
import com.diane.manage.service.ShopCollectionService;
import com.diane.manage.vo.shop.ShopCollectionRespVo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCollectionServiceImpl implements ShopCollectionService {
    @Autowired
    private ShopCollectionMapper shopCollectionMapper;
    @Override
    public int addShopCollection(ShopCollection shopCollection) {
        Long id =shopCollectionMapper.findCollectionId(shopCollection.getOpenid(),shopCollection.getShopId());
        if (id==null){
        return shopCollectionMapper.addShopCollection(shopCollection);

        }
        return 0;
    }

    @Override
    public int updateShopCollection(ShopCollection shopCollection) {
        return shopCollectionMapper.deleteCollection(shopCollection.getOpenid(),shopCollection.getShopId());
       // return shopCollectionMapper.updateShopCollection(shopCollection);
    }

    @Override
    public Page<ShopCollectionRespVo> findCollectionPage(String openid,Long regionId) {
        return shopCollectionMapper.findCollectionPage(openid,regionId);
    }
}
