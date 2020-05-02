package com.diane.manage.dao;

import com.diane.manage.model.Wallet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface WalletMapper {
    int addWallet(Wallet wallet);
    int updateWallet(Wallet wallet);
    int recordCompanyBalance(Wallet wallet);
    int recordDeliveryBalance(Wallet wallet);
    Integer findWalletBalance(@Param(value = "deliveryId")Long deliveryId,@Param(value = "companyId")Long companyId);
    Integer findCompanyBalance(@Param(value = "companyId")Long companyId);
    int companyWithdraw(Wallet wallet);
    int deliveryWithdraw(Wallet wallet);
}
