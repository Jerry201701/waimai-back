package com.diane.manage.service.impl;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.dao.AddressInfoMapper;
import com.diane.manage.service.AddressInfoService;
import com.diane.manage.vo.AddressQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressInfoServiceImpl implements AddressInfoService {
    @Autowired
    private AddressInfoMapper addressInfoMapper;
    @Override
    public int saveAddressInfo(AddressQuery addressQuery) {
        addressQuery.setFlag(false);
        if (addressQuery.getSetDefault()!=null&&addressQuery.getSetDefault()){
            addressInfoMapper.changeDefaultAddress(addressQuery);
        }

        return addressInfoMapper.insertSelective(addressQuery);
    }

    @Override
    public int updateAddressInfo(AddressQuery addressQuery) {
        return addressInfoMapper.updateByPrimaryKeySelective(addressQuery);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    public int deleteAddressInfo(List<AddressQuery> list) {
        return 0;
    }

    @Override
    public List<AddressQuery> listAddressInfoConditions(AddressQuery addressQuery) {
        return addressInfoMapper.listAddressInfoCondition(addressQuery);
    }
}
