package com.diane.manage.dao;

import com.diane.manage.model.ShopCollection;
import com.diane.manage.vo.AddressQuery;
import com.diane.manage.vo.shop.ShopCollectionRespVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ShopCollectionMapper {
    int addShopCollection(ShopCollection shopCollection);
    int updateShopCollection(ShopCollection shopCollection);
    Page<ShopCollectionRespVo> findCollectionPage(@Param(value = "openid") String openid,@Param(value = "regionId")Long regionId);
    Boolean findCollectionStatus(@Param(value = "openid")String openid,@Param(value = "shopId")Long shopId);
    Long findCollectionId(@Param(value = "openid")String openid,@Param(value = "shopId")Long shopId);
    int deleteCollection(@Param(value = "openid")String openid,@Param(value = "shopId")Long shopId);

}
