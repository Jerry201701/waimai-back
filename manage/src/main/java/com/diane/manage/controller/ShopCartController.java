package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.manage.model.GoodCategory;
import com.diane.manage.model.ShopCart;
import com.diane.manage.service.*;
import com.diane.manage.vo.PageVo;
import com.diane.manage.vo.QueryCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(value = "/customer/shop")
public class ShopCartController {
    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DeliveryRecordService deliveryRecordService;
    @RequestMapping(value = "/customer/shop/cart/edit")
    public HttpResult editShopCart(@RequestBody ShopCart shopCart){
        if (shopCart.getType()==null){
            return HttpResult.error("类型不明的操作");
        }
        if (shopCart.getType()==3){
            List<Long> list=shopCartService.findAllCartId(shopCart.getOpenid(),shopCart.getShopId());
            for (Long id:list){

                shopCartService.cleanShopCart(id);
            }
                return HttpResult.ok("清空成功");
        }
        if (shopCart.getCartId()==null||shopCart.getCartId()==0){
            return  HttpResult.ok(shopCartService.insertSelective(shopCart));
        }

        return HttpResult.ok(shopCartService.updateByPrimaryKeySelective(shopCart));
    }

    @PostMapping(value = "/customer/shop/cart/list")
    public HttpResult showShopCartDetail(@RequestBody PageVo pageVo){
        if (pageVo.getOpenid()==null||pageVo.getOpenid().isEmpty()){
            return HttpResult.error("不明身份");
        }
        if (pageVo.getShopId()==null){
            return HttpResult.ok(shopCartService.listCartByOpenid(pageVo.getOpenid()));
        }
        return HttpResult.ok(shopCartService.singleShopCartInfo(pageVo.getOpenid(),pageVo.getShopId()));

       // ShopCart shopCart=new ShopCart();
      //  shopCart.setOpenid(pageVo.getOpenid());
       /// shopCart.setShopId(pageVo.getShopId());
      //  return HttpResult.ok(shopCartService.showShopCartDetail(shopCart));
     //   return HttpResult.ok(shopCartService.shopCartList(shopCart));
    }

    @GetMapping(value = "/customer/shop/detail")
    public  HttpResult getShopInfoDetail(@RequestParam(value = "openid") String openid,
                                         @RequestParam(value = "shopId")Long shopId){
        if (openid ==null || openid.isEmpty()){
            return HttpResult.error(1,"没有openid");
        }

      //  return HttpResult.ok(shopService.getShopDetails(shopId));
        return HttpResult.ok(shopService.getShopDetailByOpenid(shopId,openid));
    }

    @GetMapping(value = "/customer/shop/good/list")
    public  HttpResult shopGoodList(@RequestParam(value = "shopId") Long shopId,
                                    @RequestParam(value = "categoryId",required = false)Long categoryId){
        return HttpResult.ok(shopCartService.listShopGood(shopId,categoryId));
    }

    @GetMapping(value = "/customer/shop/category/list")
    public HttpResult categoryList(@RequestParam(value = "shopId")Long shopId){
        GoodCategory goodCategory=new GoodCategory();
        goodCategory.setShopId(shopId);
     //  List<QueryCategoryVo>list= categoryService.listCategory(shopId);
        //List<QueryCategoryVo>list=goodCategoryService.listGoodCategory(goodCategory);
        return HttpResult.ok(categoryService.listCategory(shopId));
    }
    @GetMapping(value = "/customer/order/counts")
    public HttpResult orderCounts(@RequestParam(value = "openid")String openid,
                                  @RequestParam(value = "regionId")Long regionId){


        return HttpResult.ok(deliveryRecordService.orderCounts(openid,regionId));
    }



}
