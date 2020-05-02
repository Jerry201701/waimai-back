package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.service.DeliveryOrderService;
import com.diane.manage.service.DeliveryRecordService;
import com.diane.manage.vo.ManagerQueryVo;
import com.diane.manage.vo.order.ManageOrderRespVo;
import com.diane.manage.vo.order.ShopOrderRespVo;
import com.diane.manage.vo.shop.ShopStatisticsRespVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    private DeliveryRecordService deliveryRecordService;
    @Autowired
    private DeliveryOrderService deliveryOrderService;
    @PostMapping(value = "/list/order")
    public HttpResult listOrder(@RequestBody PageRequest pageRequest){

        Page<ShopOrderRespVo> page=deliveryRecordService.listShopOrderByManager(pageRequest);
        PageInfo<ShopOrderRespVo>pageInfo=new PageInfo<>(page);
        if (pageRequest.getPageNum()>pageInfo.getPages()){
            pageInfo.setList(null);
        }

//        System.out.println(page.getPages());
//        if (page.getPages()<pageRequest.getPageNum()){
//
//            return HttpResult.ok(null);
//        }
//        PageResult pageResult=new PageResult();
//        pageResult.setContent(page);

        return HttpResult.ok(pageInfo);
    }
    @GetMapping(value = "/show/order/detail")
    public HttpResult showOrderDetail(@RequestParam(value = "id")Long id){




        return HttpResult.ok(deliveryRecordService.showOrderDetailByManager(id));
    }
    @PostMapping(value = "/statistics")
    public HttpResult shopStatisticsByManager(@RequestBody ManagerQueryVo managerQueryVo){
        PageHelper.startPage(managerQueryVo.getPageNum(),managerQueryVo.getPageSize());
        PageInfo<ShopStatisticsRespVo> pageInfo=new PageInfo<>(deliveryOrderService.managerShopStatistics(managerQueryVo));
        if (managerQueryVo.getPageNum()>pageInfo.getPages()){

            return HttpResult.ok(null);
        }
        return HttpResult.ok(pageInfo);


    }

}
