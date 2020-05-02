package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.manage.service.AddressInfoService;
import com.diane.manage.vo.AddressQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer/consumer/address")
public class AddressInfoController {
    @Autowired
    private AddressInfoService addressInfoService;
    @PostMapping(value = "/edit")
    public HttpResult editAddressInfo(@RequestBody AddressQuery addressQuery){
        if (addressQuery.getAddressId()==null||addressQuery.getAddressId()==0){
        return HttpResult.ok(addressInfoService.saveAddressInfo(addressQuery));
        }
    return HttpResult.ok(addressInfoService.updateAddressInfo(addressQuery));
    }
    @GetMapping(value = "/show")
    public HttpResult showAddressInfo(@RequestParam(value = "openid")String openid,
                                      @RequestParam(value = "regionId")Long regionId){
        AddressQuery addressQuery=new AddressQuery();
        addressQuery.setOpenid(openid);
        addressQuery.setRegionId(regionId);
        return HttpResult.ok(addressInfoService.listAddressInfoConditions(addressQuery));
    }

}
