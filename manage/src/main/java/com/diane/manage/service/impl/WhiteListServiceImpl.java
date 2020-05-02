package com.diane.manage.service.impl;

import com.diane.manage.dao.WhiteListMapper;
import com.diane.manage.model.Delivery;
import com.diane.manage.model.WhiteList;
import com.diane.manage.service.WhiteListService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class WhiteListServiceImpl implements WhiteListService {
    @Autowired
    private WhiteListMapper whiteListMapper;
    @Override
    public int updateWhiteList(WhiteList whiteList) {
        whiteList.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        return whiteListMapper.updateWhiteList(whiteList);
    }

    @Override
    public int addWhiteList(WhiteList whiteList) {

        return whiteListMapper.addWhiteList(whiteList);
    }

    @Override
    public Page<WhiteList> findWhiteListByPage() {
        return whiteListMapper.findWhiteListByPage();
    }

    @Override
    public Integer findSingleWhiteList(Long shopId, Long deliveryId) {
        return whiteListMapper.findSingleWhiteList(shopId,deliveryId);
    }

    @Override
    public Page<Delivery> findBlackListByShop(Long shopId) {
        return whiteListMapper.findBlackListByShop(shopId);
    }
}
