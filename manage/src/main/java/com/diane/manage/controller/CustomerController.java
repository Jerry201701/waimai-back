package com.diane.manage.controller;

import com.alibaba.fastjson.JSON;
import com.diane.common.http.HttpResult;
import com.diane.manage.model.AssembleActivity;
import com.diane.manage.model.BargainActivity;
import com.diane.manage.model.BargainRecord;
import com.diane.manage.model.ShopCollection;
import com.diane.manage.service.*;
import com.diane.manage.util.CosUploadUtil;
import com.diane.manage.util.HttpClientUtil;
import com.diane.manage.vo.CodeVo;
import com.diane.manage.vo.activity.ActivityVo;
import com.diane.manage.vo.activity.BargainActivityRespVo;
import com.diane.manage.vo.query.ActivityQuery;
import com.diane.manage.vo.shop.SearchShopVo;
import com.diane.manage.vo.shop.ShopCollectionRespVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private MarketActivityService marketActivityService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CompanyInfoService companyInfoService;
    @Autowired
    private ShopCollectionService shopCollectionService;
    @Autowired
    private DeliveryRecordService deliveryRecordService;
    @Autowired
    private BargainActivityService bargainActivityService;
    @Autowired
    private BargainRecordService bargainRecordService;
    @Autowired
    private AssembleActivityService assembleActivityService;



    @GetMapping(value = "/customer/promotion/list")
    public HttpResult promotionList(@RequestParam(value = "regionId")Long regionId){

        return HttpResult.ok(marketActivityService.listPromotion(regionId));

    }
    @GetMapping(value = "/customer/promotion/detail")
    public HttpResult promotionDetail(@RequestParam(value = "activityId")Long activityId){

        return HttpResult.ok(marketActivityService.findPromotionDetail(activityId));
    }

    /*
    @PostMapping(value = "/customer/customer/bargain/create")
    public HttpResult bargainCreate(@RequestBody ActivityVo activityVo){
        return HttpResult.ok();
    }

 @PostMapping(value = "/customer/customer/bargain/bargain")
    public HttpResult bargain(@RequestBody ActivityVo activityVo){
        return HttpResult.ok();
    }


 @PostMapping(value = "/customer/customer/bargain/record")
    public HttpResult bargainRecords(@RequestBody ActivityVo activityVo){
        return HttpResult.ok();
    }

 @PostMapping(value = "/customer/customer/bargain/list/my")
    public HttpResult listBargain(@RequestBody ActivityVo activityVo){
        return HttpResult.ok();
    }

   */



    @GetMapping(value = "/customer/search")
    public HttpResult shopSearch(@RequestParam(value = "type")Integer type,
                                 @RequestParam(value = "keyWord")String keyWord,
                                 @RequestParam(value = "pageNum") Integer pageNum,
                                 @RequestParam(value = "pageSize")Integer pageSize,
                                 @RequestParam(value = "regionId") Long regionId){
        PageHelper.startPage(pageNum,pageSize);
     if (type==1){
         return HttpResult.ok(companyInfoService.searchShopByCompany(regionId, keyWord));
      //  return HttpResult.ok(companyInfoService.searchShopByKeyWord(keyWord,regionId));
     }
     if (type==2){
        return HttpResult.ok(companyInfoService.searchShopByGood(regionId,keyWord));

     }
    return HttpResult.error("查询失败");
    }


    @GetMapping(value = "/customer/shop/list")
    public HttpResult customerShopList(@RequestParam(value = "pageNum")Integer pageNum,
                                       @RequestParam(value = "pageSize")Integer pageSize,
                                       @RequestParam(value = "sort") Boolean sort,
                                       @RequestParam(value = "regionId")Long regionId){

        PageHelper.startPage(pageNum,pageSize);
        int type=0;
        Page<SearchShopVo> page=shopService.sortShopList(regionId,type,sort);
        if (pageNum>page.getPages()){
            return HttpResult.ok(null);
        }
        return HttpResult.ok(page);
    }
    @PostMapping(value = "/customer/shop/fav")
    public HttpResult shopCollection(@RequestBody ShopCollection shopCollection){
     if (shopCollection.getType()==1){
         shopCollection.setCreateTime(new Timestamp(System.currentTimeMillis()));
         shopCollection.setFlag(true);
         return  HttpResult.ok(shopCollectionService.addShopCollection(shopCollection));
     }
     if (shopCollection.getType()==0){
         return HttpResult.ok(shopCollectionService.updateShopCollection(shopCollection));

     }

    return  HttpResult.error("收藏失败");
    }
    /*
    收藏门店列表
     */
    @PostMapping(value = "/customer/good/collect")
    public HttpResult findCollectionPage(@RequestBody ShopCollection shopCollection){
     PageHelper.startPage(shopCollection.getPageNum(),shopCollection.getPageSize());
     Page<ShopCollectionRespVo> page=shopCollectionService.findCollectionPage(shopCollection.getOpenid(),shopCollection.getRegionId());

        if (shopCollection.getPageNum()>page.getPages()){
            return HttpResult.ok(null);
        }
        return HttpResult.ok(page);

    }

    /*
    积分记录展示
     */
    @GetMapping(value = "/customer/integral/show")
    public HttpResult integralShow(@RequestParam(value = "openid")String openid,
                                   @RequestParam(value = "pageNum")Integer pageNum,
                                   @RequestParam(value = "pageSize")Integer pageSize){
            PageHelper.startPage(pageNum,pageSize);

        return HttpResult.ok(deliveryRecordService.countAmountByCustomer(openid));




    }
    @GetMapping(value = "/customer/consume/record")
    public HttpResult consumeRecord(@RequestParam(value = "openid")String openid,
                                    @RequestParam(value = "comsumeTime") String comsumeTime,
                                    @RequestParam(value = "payWay")Integer payWay,
                                    @RequestParam(value = "pageNum")Integer pageNum,
                                    @RequestParam(value = "pageSize")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);


        return HttpResult.ok(deliveryRecordService.listConsumptionRecord(openid,comsumeTime,payWay));
    }
    @GetMapping(value = "/customer/consume/record/total")
    public HttpResult countTotalAmount(@RequestParam(value = "openid")String openid,
                                    @RequestParam(value = "comsumeTime") String comsumeTime,
                                    @RequestParam(value = "payWay")Integer payWay){



        return HttpResult.ok(deliveryRecordService.countTotalAmount(openid,comsumeTime,payWay));
    }
//    @GetMapping(value = "/customer/code/save")
//    public  HttpResult saveCode(@RequestParam(value = "token")String token){
//        Map<String,Object> map=new HashMap<>();
//        map.put("page","pages/index/index");
//        map.put("scene","50");
//        map.put("width",600);
//        String url="https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=".concat(token);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        try {
//            HttpPost httpPost = new HttpPost(url);
//            StringEntity entity = new StringEntity(JSON.toJSONString(map), ContentType.APPLICATION_JSON);
//            httpPost.setEntity(entity);
//            response = httpClient.execute(httpPost);
//            InputStream stream=response.getEntity().getContent();
//            String result= CosUploadUtil.SimpleUploadFileFromStream(stream);
//            System.out.println(result);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                response.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//
//        return HttpResult.ok();
//    }
//
    @GetMapping(value = "/customer/code/test")
    public HttpResult codeTest(@RequestParam(value = "token")String token){
       String url=CosUploadUtil.saveCode(token,"30");

        return HttpResult.ok(url);
    }
    @PostMapping(value = "/customer/bargain/create")
    public  HttpResult createBargain(@RequestBody BargainActivity bargainActivity){



        return HttpResult.ok(bargainActivityService.addBargainActivity(bargainActivity));
    }
    @PostMapping(value = "/customer/bargain/bargain")
    public HttpResult helpBargain(@RequestBody BargainRecord bargainRecord){
        Integer i=bargainRecordService.addBargainRecord(bargainRecord);
        if (i==2){
            return HttpResult.error("砍过了不能再砍了");
        }
        if (i==3){
            return HttpResult.error("砍到低价了不能再砍了");
        }
        if (i==4){
            return HttpResult.error("超过砍价次数限制了");
        }
        if (i==5){
            return HttpResult.error("砍价活动已过有效期");


        }

        return HttpResult.ok(bargainRecordService.addBargainRecord(bargainRecord));
    }

    @PostMapping(value = "/customer/bargain/records")
    public HttpResult bargainRecords(@RequestBody ActivityQuery activityQuery){



        return HttpResult.ok(bargainRecordService.findBargainRecordById(activityQuery.getBargainId()));
    }


    @PostMapping(value = "/customer/bargain/list/my")
    public HttpResult bargainList(@RequestBody ActivityQuery activityQuery){
        PageHelper.startPage(activityQuery.getPageNum(),activityQuery.getPageSize());
        List<BargainActivityRespVo>list=bargainActivityService.findBargainActivityByPage(activityQuery.getOpenid());
        PageInfo pageInfo = new PageInfo(list);
        if (pageInfo.getTotal()==0){
            return HttpResult.ok(pageInfo.getList());
        }
        return HttpResult.ok(pageInfo);

     //   return HttpResult.ok(bargainActivityService.findBargainActivityByPage(activityQuery.getOpenid()));
    }

    @PostMapping(value = "/customer/assemble/create")
    public HttpResult createAssembleActivity(@RequestBody AssembleActivity assembleActivity){
        Long assembleId=assembleActivityService.addAssembleActivity(assembleActivity);
        if (assembleId==null){
            return HttpResult.error("已经拼过了");
        }

        return HttpResult.ok(assembleId);
    }

    /*
  拼团列表
   */
    @PostMapping(value = "/customer/assemble/list")
    public HttpResult assembleList(@RequestBody ActivityVo activityVo){



        return HttpResult.ok(assembleActivityService.listCreateAssemble(activityVo.getOpenid()));
    }
    /*
   拼团详情
    */
    @PostMapping(value = "/customer/assemble/detail")
    public HttpResult assembleDetail(@RequestBody ActivityVo activityVo){




        return HttpResult.ok(assembleActivityService.findAssembleDetail(activityVo.getAssembleId()));
    }


    @GetMapping(value = "/customer/shop/search")
    public HttpResult shopSearch(@RequestParam(value = "keyWord") String keyWord,
                                 @RequestParam(value = "categoryId") Long categoryId,
                                 @RequestParam(value = "regionId")Long regionId,
                                 @RequestParam(value = "pageNum")Integer pageNum,
                                 @RequestParam(value = "pageSize")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return HttpResult.ok(shopService.searchShopByKeyWord(regionId,keyWord));
    }



}
