package com.diane.manage.dao;

import com.diane.manage.model.SettlingModel;
import com.diane.manage.vo.delivery.DeliveryRuleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SettlingModelMapper {
    int addSettlingModel(SettlingModel settlingModel);
    int updateByPrimaryKeySelective(SettlingModel settlingModel);
    List<SettlingModel> findPageByRegion(@Param(value = "regionId")Long regionId);
    List<String> findCheck(@Param(value = "settlementId")Long settlementId);
    int removeSettlementRecord(@Param(value = "settlementId")Long settlementId);
    SettlingModel findSettlementByShop(@Param(value ="shopId")Long shopId);


}
