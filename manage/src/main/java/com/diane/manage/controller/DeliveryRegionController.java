package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.manage.model.DeliveryRegion;
import com.diane.manage.service.DeliveryRegionService;
import com.diane.manage.service.GoodInfoService;
import com.diane.manage.vo.shop.ShopReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class DeliveryRegionController {
    @Autowired
    private DeliveryRegionService deliveryRegionService;
    @Autowired
    private GoodInfoService goodInfoService;
    @PostMapping(value = "/add")
    public HttpResult saveDeliveryRegion(@RequestBody DeliveryRegion deliveryRegion){
        if (deliveryRegion.getId()==null||deliveryRegion.getId()==0){
            return HttpResult.ok(deliveryRegionService.saveDeliveryRegion(deliveryRegion));
        }

        return HttpResult.ok(deliveryRegionService.updateDeliveryRegion(deliveryRegion));
    }
    @PostMapping(value = "/findPage")
    public HttpResult findPageDeliveryRegion(@RequestBody PageRequest pageRequest){

        return HttpResult.ok(deliveryRegionService.findPage(pageRequest));
    }
    @PostMapping(value = "/delete")
    public HttpResult deleteDeliveryRegion(@RequestBody List<DeliveryRegion> list){
        return  HttpResult.ok(deliveryRegionService.deleteDeliveryRegion(list));
    }
    @GetMapping(value = "/list")
    public HttpResult listRegions(){
        return HttpResult.ok(deliveryRegionService.listRegions());
    }


    @PostMapping(value = "/cone")
    public HttpResult copyGood(@RequestBody ShopReqVo shopReqVo){

        return  HttpResult.ok(goodInfoService.copyShopGood(shopReqVo));
    }
}
