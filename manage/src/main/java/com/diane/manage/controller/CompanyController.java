package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.manage.model.CompanyInfo;
import com.diane.manage.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyInfoService companyInfoService;


    @PreAuthorize("hasAuthority('account:company:view')")
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {

        return HttpResult.ok(companyInfoService.findPage(pageRequest));
    }


  //  @PreAuthorize("hasAuthority('account:company:add') AND hasAuthority('account:compay:edit')")
    @PreAuthorize("hasAuthority('account:company:add')")
    @PostMapping(value = "/add")
    public HttpResult addCompany(@RequestBody CompanyInfo companyInfo){
        if (companyInfo.getId()==null||companyInfo.getId()==0){
            Long id=companyInfoService.saveCompanyInfo(companyInfo);
            Map<String,Long>map=new HashMap<>();
//            if (companyInfo.getRegisterType()==1){
//            map.put("merId",id);
//            }
//            if(companyInfo.getRegisterType()==2){
//                map.put("deliveryId",id);
//            }
        return HttpResult.ok(map);
        }

        return HttpResult.ok(companyInfoService.updateCompanyInfo(companyInfo));
    }


    @PreAuthorize("hasAuthority('manage:company:add')")
    @PostMapping(value = "/delete")
    public HttpResult deleteCompany(@RequestBody List<CompanyInfo> companyInfos){
        return HttpResult.ok(companyInfoService.deleteCompanyInfo(companyInfos));
    }

    @PreAuthorize("hasAuthority('account:company:view')")
    @GetMapping(value = "/list/user")
    public HttpResult listCompanyByUser(@RequestParam(value = "userId") Long userId){
       return HttpResult.ok(companyInfoService.listCompanyByUser(userId));

    }


    @GetMapping(value = "/basic/info")
    public HttpResult showCompanyBasicInfo(@RequestParam(value = "companyId")Long companyId){

        return HttpResult.ok(companyInfoService.showCompanyBasicInfo(companyId));
    }


    @GetMapping(value = "/test")
    public String testSecurityController(){
       return "success";
    }










}
