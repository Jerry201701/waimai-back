package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.manage.model.Advertisement;
import com.diane.manage.service.AdvertisementService;
import com.diane.manage.service.CategoryService;
import com.diane.manage.service.NavInfoService;
import com.diane.manage.service.NoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertisementController {
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private NoticeInfoService noticeInfoService;
    @Autowired
    private NavInfoService navInfoService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/advertisement/add")
    public HttpResult saveAdvertisement(@RequestBody Advertisement advertisement){
        if (advertisement.getId()==null||advertisement.getId()==0){
            return HttpResult.ok(advertisementService.saveAdvertisement(advertisement));
        }
        return HttpResult.ok(advertisementService.updateAdvertisement(advertisement));
    }

  //  @PreAuthorize("hasAuthority('manage:company:view')")
    @PostMapping(value="/advertisement/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(advertisementService.findPage(pageRequest));
    }


    @PostMapping(value = "/advertisement/delete")
    public HttpResult deleteCompany(@RequestBody List<Advertisement> advertisements){
        return HttpResult.ok(advertisementService.deleteAdvertisement(advertisements));
    }
    @GetMapping(value = "/customer/advertisement/list")
    public  HttpResult listAdvertisement(@RequestParam(value = "regionId")Long regionId){
        return HttpResult.ok(advertisementService.listAdvertisement(regionId));
    }

    @GetMapping(value = "customer/notice/detail")
    public HttpResult getOneNotice(@RequestParam(value = "regionId")Long regionId){

        return HttpResult.ok(noticeInfoService.getOneNotice(regionId));
    }
    @GetMapping(value = "/customer/nav/list")
    public HttpResult listNavByRegion(@RequestParam(value = "regionId")Long regionId){

        return HttpResult.ok(navInfoService.listNavByRegion(regionId));
    }
    @GetMapping(value = "/customer/region/category/list")
    public HttpResult listCategoryByRegion(@RequestParam(value = "regionId")Long regionId){
        return HttpResult.ok(categoryService.findCategoryByRegion(regionId));

    }


}
