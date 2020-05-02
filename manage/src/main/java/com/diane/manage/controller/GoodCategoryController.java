package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.manage.model.GoodCategory;
import com.diane.manage.service.GoodCategoryService;
import com.diane.manage.vo.QueryCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test/shop/good/category")
public class GoodCategoryController {
    @Autowired
    private GoodCategoryService goodCategoryService;
    @PostMapping(value = "/edit")
    public HttpResult saveGoodCategory(@RequestBody QueryCategoryVo queryCategoryVo){
       // BeanUtils.copyProperties(source, target);
        GoodCategory goodCategory=new GoodCategory();

        BeanUtils.copyProperties(queryCategoryVo,goodCategory);
        if (queryCategoryVo.getCategoryId()==null||queryCategoryVo.getCategoryId()==0){
            goodCategory.setFlag(false);
        return HttpResult.ok(goodCategoryService.saveGoodCategory(goodCategory));
        }
        goodCategory.setId(queryCategoryVo.getCategoryId());
        return HttpResult.ok(goodCategoryService.updateGoodCategory(goodCategory));

    }
    @GetMapping(value = "/list")
    public HttpResult listGoodCategory(@RequestParam(value = "shopId")Long shopId){
        GoodCategory goodCategory=new GoodCategory();
        goodCategory.setShopId(shopId);
        return HttpResult.ok(goodCategoryService.listGoodCategory(goodCategory));
    }
}
