package com.diane.manage.service.impl;

import com.diane.manage.dao.FullReductionMapper;
import com.diane.manage.model.FullReduction;
import com.diane.manage.service.FullReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class FullReductionServiceImpl implements FullReductionService {
    @Autowired
    private FullReductionMapper fullReductionMapper;

    @Override
    public int insertSelective(FullReduction fullReduction) {
        fullReduction.setFlag(false);
        fullReduction.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return fullReductionMapper.insertSelective(fullReduction);
    }

    @Override
    public int updateByPrimaryKeySelective(FullReduction fullReduction) {
        return fullReductionMapper.updateByPrimaryKeySelective(fullReduction);
    }

    @Override
    public List<FullReduction> listFullReductionByShop(Long shopId) {
        return fullReductionMapper.listFullReductionByShop(shopId);
    }

    @Override
    public int removeRecord(Long activityId) {
        return fullReductionMapper.removeRecord(activityId);
    }

    @Override
    public FullReduction findSingleFullReductionByShop(Long shopId,Integer amount) {
        return fullReductionMapper.findSingleFullReductionByShop(shopId,amount);
    }
}
