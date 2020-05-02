package com.diane.manage.dao;

import com.diane.manage.vo.AddressQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Mapper
public interface AddressInfoMapper {
    int insertSelective(AddressQuery addressQuery);
    int updateByPrimaryKeySelective(AddressQuery addressQuery);
    List<AddressQuery> listAddressInfoCondition(AddressQuery addressQuery);
    Integer countAddressInfoCondition(AddressQuery addressQuery);
    int  changeDefaultAddress(AddressQuery addressQuery);




}
