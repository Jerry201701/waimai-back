package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.manage.model.MarketActivity;
import com.diane.manage.service.MarketActivityService;
import com.diane.manage.vo.activity.ActivityRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/activity")
public class MarketActivityController {
    @Autowired
    private MarketActivityService marketActivityService;
    @PostMapping(value = "/save")
    public HttpResult editActivity(@RequestBody MarketActivity marketActivity){
        if (marketActivity.getId()==null||marketActivity.getId()==0){
            marketActivity.setFlag(true);
            marketActivity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            return HttpResult.ok(marketActivityService.insertSelective(marketActivity));
        }
        return HttpResult.ok(marketActivityService.updateByPrimaryKeySelective(marketActivity));
    }
    @PostMapping(value = "/find/page")
    public HttpResult findActivityPage(@RequestBody PageRequest pageRequest){


        return HttpResult.ok(marketActivityService.findPage(pageRequest));

    }

    @PostMapping(value = "/delete")
    public  HttpResult deleteNavInfo(@RequestBody List<MarketActivity> list){
        for (MarketActivity marketActivity:list){
            marketActivityService.deleteActivityInfo(marketActivity);
        }
        return HttpResult.ok();
    }
    @GetMapping(value = "/find/shop")
    public  HttpResult findShopsByRegion(@RequestParam(value = "regionId")Long regionId){
    List<ActivityRespVo> list =marketActivityService.findShopByRegion(regionId);
      return   HttpResult.ok(list);
    }
    @GetMapping(value = "/find/good")
    public HttpResult findGoodsByShop(@RequestParam(value = "shopId")Long shopId){

        return HttpResult.ok(marketActivityService.findGoodByShop(shopId));
    }



}
