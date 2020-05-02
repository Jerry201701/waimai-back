package com.diane.manage.service.impl;

import com.diane.common.page.ColumnFilter;
import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.CompanyMapper;
import com.diane.manage.dao.GoodMapper;
import com.diane.manage.dao.SettlingModelMapper;
import com.diane.manage.dao.ShopMapper;
import com.diane.manage.model.SettlingModel;
import com.diane.manage.service.SettlingModelService;
import com.diane.manage.vo.delivery.DeliveryRuleVo;
import com.diane.manage.vo.income.CompanyTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class SettlingModelServiceImpl implements SettlingModelService {
    @Autowired
    private SettlingModelMapper settlingModelMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Override
    public int insertSelective(SettlingModel settlingModel) {
        settlingModel.setFlag(true);
        settlingModel.setCreateTime(new Timestamp(System.currentTimeMillis()));
        List<String>units=settlingModel.getUnitId();
        int i=settlingModelMapper.addSettlingModel(settlingModel);
        for (String id:units){
            if (id.startsWith("C")){
                companyMapper.companySettlementWay(settlingModel.getId(),Long.valueOf(id.substring(1)));
            }
            if (id.startsWith("S")){
                shopMapper.shopDeliverySettlementWay(settlingModel.getId(),Long.valueOf(id.substring(1)));
            }
            if (id.startsWith("G")){
                goodMapper.goodDeliverySettlementWay(settlingModel.getId(),Long.valueOf(id.substring(1)));
            }

        }
        return i;
    }

    @Override
    public int updateByPrimaryKeySelective(SettlingModel settlingModel) {
        settlingModel.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        return settlingModelMapper.updateByPrimaryKeySelective(settlingModel);
    }

    @Override
    public PageResult findPageByRegion(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilter("label");
        return MybatisPageHelper.findPage(pageRequest, settlingModelMapper,"findPageByRegion",Long.valueOf(columnFilter.getValue()));
    }

    @Override
    public List<CompanyTreeVo> listCompanyTree(Long regionId) {
        return companyMapper.listCompanyTree(regionId);
    }

    @Override
    public List<CompanyTreeVo> listShopsByCompany(Long companyId) {
        return shopMapper.listShopsByCompany(companyId);
    }

    @Override
    public List<CompanyTreeVo> listGoodsByShop(Long shopId) {
        return goodMapper.findGoodByShop(shopId);
    }

    @Override
    public List<String> findCheck(Long settlementId) {
        return settlingModelMapper.findCheck(settlementId);
    }

    @Override
    public int removeSettlementRecord(Long settlementId) {
        return settlingModelMapper.removeSettlementRecord(settlementId);
    }

    @Override
    public SettlingModel findSettlementByShop(Long shopId) {
        return settlingModelMapper.findSettlementByShop(shopId);
    }

    @Override
    public Integer computeDeliveryFee(Long shopId,Integer amount) {
        DeliveryRuleVo deliveryRuleVo=shopMapper.findDeliveryRuleByShop(shopId);
        if (deliveryRuleVo.getType()){
            return deliveryRuleVo.getFixValue();
        }
        BigDecimal fee=BigDecimal.valueOf(deliveryRuleVo.getRateValue()*amount)
                .divide(BigDecimal.valueOf(100))
                .setScale(0,BigDecimal.ROUND_HALF_UP);
        Integer deliveryFee=fee.intValue();
        if (deliveryFee>deliveryRuleVo.getMaxValue()){
            return deliveryRuleVo.getMaxValue();
        }
        if (deliveryFee<deliveryRuleVo.getMinValue()){
            return deliveryRuleVo.getMinValue();
        }

      //  fee=fee.divide(BigDecimal.valueOf(100));
      //  fee=fee.setScale(0,BigDecimal.ROUND_HALF_UP);



        return deliveryFee;
    }
}
