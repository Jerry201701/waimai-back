package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.CompanyInfo;
import com.diane.manage.vo.AddressQuery;

import java.util.List;

public interface AddressInfoService {
    int saveAddressInfo(AddressQuery addressQuery);
    int updateAddressInfo(AddressQuery addressQuery);
    PageResult findPage(PageRequest pageRequest);
    int deleteAddressInfo(List<AddressQuery> list);
    List<AddressQuery> listAddressInfoConditions(AddressQuery addressQuery);
}
