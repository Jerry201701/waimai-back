package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.manage.model.SettlingModel;
import com.diane.manage.service.SettlingModelService;
import com.diane.manage.service.ShopService;
import com.diane.manage.vo.shop.RuleShopVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/settling")
public class SettlingModelController {
    @Autowired
    private SettlingModelService settlingModelService;
    @Autowired
    private ShopService shopService;
    @PostMapping(value = "/add")
    public HttpResult editSettlingModel(@RequestBody SettlingModel settlingModel){
        if (settlingModel.getId()==null||settlingModel.getId()==0){

            return HttpResult.ok(settlingModelService.insertSelective(settlingModel));
        }

        return HttpResult.ok(settlingModelService.updateByPrimaryKeySelective(settlingModel));
    }

    @PostMapping(value = "/delete")
    public  HttpResult deleteSettlingModer(@RequestBody List<SettlingModel> settlingModels){
        for (SettlingModel settlingModel:settlingModels){

            settlingModelService.removeSettlementRecord(settlingModel.getId());
        }

        return  HttpResult.ok();

    }
    @PostMapping(value = "/find/page")
    public HttpResult findPage(@RequestBody PageRequest request){



        return HttpResult.ok(settlingModelService.findPageByRegion(request));
    }

    @GetMapping(value = "/list/tree")
    public HttpResult listCompanyTree(@RequestParam(value = "regionId")Long regionId){



        return HttpResult.ok(settlingModelService.listCompanyTree(regionId));
    }

    @GetMapping(value = "/find/shops")
    public HttpResult findShops(@RequestParam(value = "companyId")String companyId){

        return HttpResult.ok(settlingModelService.listShopsByCompany(Long.valueOf(companyId.substring(1))));
    }

    @GetMapping(value = "/find/goods")
    public HttpResult findGoods(@RequestParam(value = "shopId")String shopId){

        return HttpResult.ok(settlingModelService.listGoodsByShop(Long.valueOf(shopId.substring(1))));
    }

    @GetMapping(value = "/check")
    public HttpResult findCheck(@RequestParam(value = "settlementId")Long settlement){


        return  HttpResult.ok(settlingModelService.findCheck(settlement));

    }

    @PostMapping(value = "/rule/shops")
    public HttpResult ruleShopsByRegion(@RequestBody RuleShopVo ruleShopVo){
        PageHelper.startPage(ruleShopVo.getPageNum(),ruleShopVo.getPageSize());
        Page<RuleShopVo>page=shopService.listShopInfoByRegion(ruleShopVo.getRegionId());
        if (page.getPages()<ruleShopVo.getPageNum()){

            return HttpResult.ok(null);
        }
       // System.out.println(page.getPages());
        return HttpResult.ok(page);




       // return HttpResult.ok(shopService.listShopInfoByRegion(ruleShopVo.getRegionId()));
    }
    @PostMapping(value = "/choose/shop")
    public HttpResult chooseShop(@RequestBody List<RuleShopVo> list){
        for (RuleShopVo ruleShopVo:list){
            shopService.addRule(ruleShopVo.getRuleId(),ruleShopVo.getShopId());
        }



        return HttpResult.ok();
    }
    @GetMapping(value = "/store/rule")
    public HttpResult listShopByRule(@RequestParam(value = "ruleId")Long ruleId){


        return HttpResult.ok(shopService.listShopsByRule(ruleId));
    }



}
