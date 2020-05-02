package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.manage.model.GoodInfo;
import com.diane.manage.service.GoodInfoService;
import com.diane.manage.vo.GoodQuery;
import com.diane.manage.vo.good.GoodQueryVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop/good")
public class GoodController {
    @Autowired
    private GoodInfoService goodInfoService;
    //@PreAuthorize("hasAuthority('account:good:add') AND hasAuthority('account:good:edit')")
    @PostMapping(value = "/add")
    public HttpResult saveGoodInfo(@RequestBody GoodInfo goodInfo){
        return HttpResult.ok(goodInfoService.saveGoodInfo(goodInfo));
    }

    //@PreAuthorize("hasAuthority('account:good:view')")
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(goodInfoService.findPage(pageRequest));
    }
    @PostMapping(value = "/page/region")
    public HttpResult goodPageInfo(@RequestBody GoodQueryVo goodQueryVo){

        PageInfo<GoodInfo> pageInfo=new PageInfo<>(goodInfoService.goodPageByRegion(goodQueryVo));
        return HttpResult.ok(pageInfo);
    }

    @PostMapping(value = "/delete")
    public HttpResult removeGoodInfo(@RequestBody List<GoodInfo> goodInfos){
        return HttpResult.ok(goodInfoService.removeGoodInfo(goodInfos));

    }
    @PostMapping(value = "/detail")
    public HttpResult getGoodDetailById(@RequestBody GoodQuery goodQuery){
        Long goodId=goodQuery.getGoodId();
        return HttpResult.ok(goodInfoService.getGoodDetailsById(goodId));
    }
    @PostMapping(value = "/edit")
    public HttpResult editGoodInfo(@RequestBody GoodInfo goodInfo){
        if (goodInfo.getId()==null||goodInfo.getId()==0){
            return HttpResult.ok(goodInfoService.saveGoodInfo(goodInfo));
        }
        return HttpResult.ok(goodInfoService.updateGoodInfo(goodInfo));

    }
    @PostMapping(value = "/list")
    public HttpResult listGoodByConditions(@RequestBody GoodQuery goodQuery){
        return HttpResult.ok(goodInfoService.listGoodByConditions(goodQuery));
    }

}
