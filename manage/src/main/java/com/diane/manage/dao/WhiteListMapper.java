package com.diane.manage.dao;

import com.diane.manage.model.Delivery;
import com.diane.manage.model.WhiteList;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface WhiteListMapper {
    int updateWhiteList(WhiteList whiteList);
    int addWhiteList(WhiteList whiteList);
    Page<WhiteList> findWhiteListByPage();
    Integer findSingleWhiteList(@Param(value = "shopId")Long shopId,@Param(value = "deliveryId")Long deliveryId);
    Page<Delivery>findBlackListByShop(@Param(value = "shopId")Long shopId);
}
