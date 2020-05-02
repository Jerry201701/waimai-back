package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.manage.model.CategoryInfo;
import com.diane.manage.service.CategoryService;
import com.diane.manage.vo.QueryCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = "/shop/good/category/edit")
    public HttpResult saveCategory(@RequestBody QueryCategoryVo queryCategoryVo){


        CategoryInfo categoryInfo =new CategoryInfo();
        categoryInfo.setCategoryName(queryCategoryVo.getCategoryName());
        categoryInfo.setId(queryCategoryVo.getCategoryId());
        categoryInfo.setCategoryType(queryCategoryVo.getCategoryType());
        categoryInfo.setShopId(queryCategoryVo.getShopId());
        if (queryCategoryVo.getFlag()!=null){
        categoryInfo.setFlag(queryCategoryVo.getFlag());

        }


        if (categoryInfo.getId()==null||categoryInfo.getId()==0){
            categoryInfo.setFlag(false);
        return  HttpResult.ok(categoryService.saveCategory(categoryInfo));
        }
        return HttpResult.ok(categoryService.updateCategory(categoryInfo));
    }
    @PostMapping(value = "/category/page/region")
    public  HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(categoryService.findPage(pageRequest));
    }
    @RequestMapping(value = "/category/delete")
    public HttpResult deleteCategory(@RequestBody List<CategoryInfo> categoryInfos){
        return HttpResult.ok(categoryService.deleteCategory(categoryInfos));
    }
    @GetMapping(value = "/shop/good/category")
    public HttpResult listAll(@RequestParam(value = "regionId") Integer  regionId){
        return HttpResult.ok(categoryService.listAll());
    }
    @GetMapping(value = "/shop/good/category/list")
    public HttpResult listCategoryByCondition(@RequestParam(value = "shopId")Long shopId){
       // CategoryInfo categoryInfo=new CategoryInfo();
       // categoryInfo.setShopId(shopId);
        return HttpResult.ok(categoryService.listCategory(shopId));
    }
    @GetMapping(value = "/category/list")
    public HttpResult findCategoryByRegion(@RequestParam(value = "regionId")Long regionId){

        return  HttpResult.ok(categoryService.findCategoryByRegion(regionId));
    }
    @PostMapping(value = "/manage/category")
    public HttpResult addRegionCategory(@RequestBody CategoryInfo categoryInfo){
        if (categoryInfo.getId()==null){
            return HttpResult.ok(categoryService.saveCategory(categoryInfo));
        }
        return HttpResult.ok(categoryService.saveCategory(categoryInfo));
    }



}
