package com.diane.manage.service.impl;

import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.DeliveryMapper;
import com.diane.manage.dao.DeliveryRecordMapper;
import com.diane.manage.dao.WalletMapper;
import com.diane.manage.model.Delivery;
import com.diane.manage.model.Wallet;
import com.diane.manage.service.DeliveryService;
import com.diane.manage.vo.delivery.DeliveryOrderRespVo;
import com.diane.manage.vo.delivery.DeliveryQueryVo;
import com.diane.manage.vo.order.PayResultDetail;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryMapper deliveryMapper;
    @Autowired
    private WalletMapper walletMapper;

    @Override
    public Long insertSelective(Delivery delivery) {
        deliveryMapper.insertSelective(delivery);
        Wallet wallet=new Wallet();
        wallet.setBalance(0);
        wallet.setDeliveryId(delivery.getDeliveryId());
        wallet.setCreateTime(new Timestamp(System.currentTimeMillis()));
        wallet.setFlag(true);
        wallet.setRoleType(false);
        walletMapper.addWallet(wallet);


        return delivery.getDeliveryId();
    }

    @Override
    public int updateByPrimaryKeySelective(Delivery delivery) {
        return deliveryMapper.updateByPrimaryKeySelective(delivery);
    }

    @Override
    public List<Delivery> findPage() {
        return null;
    }

    @Override
    public int deleteDelivery(Delivery delivery) {
        return 0;
    }

    @Override
    public Delivery showDeliveryInfoById(Long deliveryId) {
        Delivery delivery= deliveryMapper.showDeliveryInfoById(deliveryId);





        return delivery;
    }


    @Override
    public Long deliveryOrderManage(DeliveryQueryVo deliveryQueryVo) {
       // return deliveryRecordMapper.insertSelective(deliveryQueryVo);
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, deliveryMapper);
    }

    @Override
    public Page<Delivery> findDeliveryByRegion(Long regionId, String keyWord,Integer type,Long shopId) {
        return deliveryMapper.findDeliveryByRegion(regionId,keyWord,type,shopId);
    }
}
