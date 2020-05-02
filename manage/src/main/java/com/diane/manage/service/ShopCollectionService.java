package com.diane.manage.service;

import com.diane.manage.model.ShopCollection;
import com.diane.manage.vo.shop.ShopCollectionRespVo;
import com.github.pagehelper.Page;

public interface ShopCollectionService {
    int addShopCollection(ShopCollection shopCollection);
    int updateShopCollection(ShopCollection shopCollection);
    Page<ShopCollectionRespVo> findCollectionPage(String openid,Long regionId);


}
