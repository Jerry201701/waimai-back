package com.diane.manage.service.impl;

import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.ShopWithdrawMapper;
import com.diane.manage.dao.WalletMapper;
import com.diane.manage.model.ShopWithdraw;
import com.diane.manage.model.Wallet;
import com.diane.manage.service.ShopWithdrawService;
import com.diane.manage.util.CommonUtil;
import com.diane.manage.util.WithdrawalUtil;
import com.diane.manage.vo.shop.WithdrawVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class ShopWithdrawServiceImpl implements ShopWithdrawService {
    public static final String APPID= "wx06e91897b9a0f762";
    public static final String DELIVERY_APPID ="wx45438c67ae3b4171";
    //微信支付的商户id
    public static final String MCH_ID= "1553040751";
    @Autowired
    private ShopWithdrawMapper shopWithdrawMapper;
    @Autowired
    private WalletMapper walletMapper;
    @Override
    public int insertSelective(ShopWithdraw shopWithdraw) {
        /*
        判断余额跟提现金额的大小
         */

        Integer balance=walletMapper.findWalletBalance(shopWithdraw.getDeliveryId(),shopWithdraw.getCompanyId());
        if (shopWithdraw.getMoney()>balance){
            return 2;

        }
        shopWithdraw.setApplyTime(new Timestamp(System.currentTimeMillis()));
        shopWithdraw.setStatus(2);
        shopWithdraw.setReason("微信转账失败");

         /*
        申请提现要做3件事：1，钱包更新余额 2，插入提现记录 3，发起转账请求
         */

        String appid=null;
                if (shopWithdraw.getType()==1){
                    appid=APPID;

                }
                if (shopWithdraw.getType()==2){
                      appid=DELIVERY_APPID;
                }
                String amount=shopWithdraw.getMoney().toString();
                 Integer result=WithdrawalUtil.withdrawRequest(appid,MCH_ID, CommonUtil.getUUID(),shopWithdraw.getOpenid(),amount,"提现");
                 if (result==1) {
                     Wallet wallet = new Wallet();
                     wallet.setBalance(balance - shopWithdraw.getMoney());
                     if (shopWithdraw.getDeliveryId() != null) {
                         wallet.setDeliveryId(shopWithdraw.getDeliveryId());
                         walletMapper.deliveryWithdraw(wallet);
                     }
                     if (shopWithdraw.getCompanyId() != null) {
                         wallet.setCompanyId(shopWithdraw.getCompanyId());
                         walletMapper.companyWithdraw(wallet);
                     }
                     shopWithdraw.setStatus(1);
                     shopWithdraw.setReason(null);
                     shopWithdrawMapper.insertSelective(shopWithdraw);

                     return 1;
                 }

        shopWithdrawMapper.insertSelective(shopWithdraw);

//        ThreadPoolExecutor executor=new ThreadPoolExecutor(2,3,1,
//                    TimeUnit.SECONDS,new LinkedBlockingDeque<>());
//            CountDownLatch latch=new CountDownLatch(2);
//
//        executor.execute(()->{
//            try {
//
//                Wallet wallet=new Wallet();
//                wallet.setBalance(balance-shopWithdraw.getMoney());
//                if (shopWithdraw.getCompanyId()==null){
//                    wallet.setDeliveryId(shopWithdraw.getDeliveryId());
//                    walletMapper.recordDeliveryBalance(wallet);
//                }
//                wallet.setCompanyId(shopWithdraw.getCompanyId());
//                walletMapper.recordCompanyBalance(wallet);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            latch.countDown();
//        });
//
//        executor.execute(()->{
//                try {
//                    shopWithdraw.setApplyTime(new Timestamp(System.currentTimeMillis()));
//                    shopWithdraw.setStatus(2);
//                    shopWithdrawMapper.insertSelective(shopWithdraw);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                latch.countDown();
//            });
//
//        executor.execute(()->{
//            try {
//                String appid=null;
//                if (shopWithdraw.getType()==1){
//                    appid=APPID;
//
//                }
//                if (shopWithdraw.getType()==2){
//                      appid=DELIVERY_APPID;
//                }
//                String amount=shopWithdraw.getMoney().toString();
//                 Integer result=WithdrawalUtil.withdrawRequest(appid,MCH_ID, CommonUtil.getUUID(),shopWithdraw.getOpenid(),amount,"提现");
//                 if (result==1){
//                     Wallet wallet=new Wallet();
//                     wallet.setBalance(shopWithdraw.getMoney());
//                    if (shopWithdraw.getDeliveryId()!=null){
//                    wallet.setDeliveryId(shopWithdraw.getDeliveryId());
//                    walletMapper.deliveryWithdraw(wallet);
//                    }
//                    if (shopWithdraw.getCompanyId()!=null){
//                        wallet.setCompanyId(shopWithdraw.getCompanyId());
//                        walletMapper.companyWithdraw(wallet);
//                    }
//
//                 }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        });
//

        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(ShopWithdraw shopWithdraw) {
        return shopWithdrawMapper.updateByPrimaryKeySelective(shopWithdraw);
    }

    @Override
    public Page<ShopWithdraw> findPageByCompany(WithdrawVo withdrawVo) {
        PageHelper.startPage(withdrawVo.getPageNum(),withdrawVo.getPageSize());

        return shopWithdrawMapper.findPageByCompany(withdrawVo);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {

        return MybatisPageHelper.findPage(pageRequest,shopWithdrawMapper);
    }

    @Override
    public int checkWithdraw(Integer status, Long withdrawId) {
        return shopWithdrawMapper.checkWithdraw(status,withdrawId);
    }
}
