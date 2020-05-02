package com.diane.manage.service;

import com.diane.manage.model.Delivery;
import com.diane.manage.model.WhiteList;
import com.github.pagehelper.Page;

public interface WhiteListService {
    int updateWhiteList(WhiteList whiteList);
    int addWhiteList(WhiteList whiteList);
    Page<WhiteList> findWhiteListByPage();
    Integer findSingleWhiteList(Long shopId,Long deliveryId);
    Page<Delivery>findBlackListByShop(Long shopId);
}
