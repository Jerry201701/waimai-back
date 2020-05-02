package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.manage.model.*;
import com.diane.manage.service.*;
import com.diane.manage.util.PasswordUtils;
import com.diane.manage.vo.PasswordVo;
import com.diane.manage.vo.PwdCheckVo;
import com.diane.manage.vo.delivery.DeliveryQueryVo;
import com.diane.manage.vo.delivery.StatisticsDeliveryVo;
import com.diane.manage.vo.shop.WithdrawVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private DeliveryRecordService deliveryRecordService;
    @Autowired
    private ShopWithdrawService shopWithdrawService;
    @Autowired
    private DeliveryOrderService deliveryOrderService;
    @Autowired
    private SysUserService sysUserService;

    @PostMapping(value = "/edit")
    public HttpResult deliveryEdit(@RequestBody Delivery delivery){
        if (delivery.getDeliveryId()==0||delivery.getDeliveryId()==null){
            return  HttpResult.ok(deliveryService.insertSelective(delivery));
        }
        return HttpResult.ok(deliveryService.updateByPrimaryKeySelective(delivery));
    }
    @RequestMapping(value = "/find/page")
    public  HttpResult findPage(PageRequest pageRequest){
        return HttpResult.ok(deliveryService.findPage(pageRequest));
    }

    @GetMapping(value = "/basic/info")
    public HttpResult showDeliveryInfoById(@RequestParam(value = "deliveryId")Long deliveryId){
        return HttpResult.ok(deliveryService.showDeliveryInfoById(deliveryId));
    }
    /*
    配送订单列表
     */
    @PostMapping(value = "/order/list")
    public  HttpResult deliveryOrderList(@RequestBody DeliveryQueryVo deliveryQueryVo){
     //   List<Long> deliverys=sysUserService.findDeliveryByKeyWord(deliveryQueryVo.getKeyWord());

        if (deliveryQueryVo.getDeliveryStatus()!=null&&deliveryQueryVo.getDeliveryStatus()==5){
            deliveryQueryVo.setDeliveryId(null);
        }
        if (deliveryQueryVo.getDeliveryStatus()!=null&&deliveryQueryVo.getDeliveryStatus()==6){
            deliveryQueryVo.setHandleStatus(5);
            deliveryQueryVo.setDeliveryStatus(null);
        }

        PageHelper.startPage(deliveryQueryVo.getPageNum(),deliveryQueryVo.getPageSize());
        return HttpResult.ok(deliveryRecordService.deliveryOrderList(deliveryQueryVo));
    }
    /*
    配送订单详情
     */
    @GetMapping(value = "/order/detail")
    public HttpResult deliveryOrderDetail(@RequestParam(value = "orderNumber")String orderNumber){

        return HttpResult.ok(deliveryRecordService.deliveryOrderDetail(orderNumber));
    }
    /*
    配送员管理订单
     */
    @PostMapping(value = "/order/manage")
    public HttpResult deliveryOrderManage(@RequestBody DeliveryShop deliveryShop) {

        if (deliveryShop.getDeliveryId() == null) {
            return HttpResult.error("没有配送员信息");
        }
        int i = deliveryRecordService.manageDeliveryStatus(deliveryShop.getOrderNumber(), deliveryShop.getDeliveryStatus(),deliveryShop.getDeliveryId());
        if (i == 1) {
            return HttpResult.ok("配送状态修改成功");
        }
        return HttpResult.error("配送状态修改失败");
    }
    @PostMapping(value = "/withdraw/apply")
    public HttpResult withdrawApply(@RequestBody ShopWithdraw shopWithdraw){
        shopWithdraw.setType(2);
        int i=shopWithdrawService.insertSelective(shopWithdraw);
        if (shopWithdraw.getMoney()<100){
            return HttpResult.error("提现低于最低限制1元");
        }
        if (i==2){
            return HttpResult.error("余额不足");
        }
        if (i==1){
            return  HttpResult.ok("提现成功");
        }
        // WithdrawalUtil.withdrawRequest();
        return HttpResult.error("提现失败");

    }
    @PostMapping(value = "/withdraw/list")
    public HttpResult withdrawPage(@RequestBody WithdrawVo withdrawVo){

        return HttpResult.ok(shopWithdrawService.findPageByCompany(withdrawVo));
    }
    @PostMapping(value = "/withdraw/feedback")
    public HttpResult withdrawFeedback(@RequestBody ShopWithdraw shopWithdraw){
        return HttpResult.ok(shopWithdrawService.updateByPrimaryKeySelective(shopWithdraw));

    }
    @PostMapping(value = "/statistics")
    public HttpResult statisticsDelivery(@RequestBody StatisticsDeliveryVo statisticsDeliveryVo){



        return HttpResult.ok(deliveryOrderService.deliveryStatistics(statisticsDeliveryVo.getDeliveryId(),statisticsDeliveryVo.getDeliveryTime()));
    }


    @PostMapping(value = "/changepwd")
    public HttpResult changePassword(@RequestBody PasswordVo passwordVo){
        Long userId=passwordVo.getUserId();
        PwdCheckVo pwdCheckVo=sysUserService.findPasswordInfo(userId);

        if (passwordVo.getType()==1){
            if (!PasswordUtils.matches(pwdCheckVo.getSalt(), passwordVo.getOldPassword(), pwdCheckVo.getPassword())) {
                return HttpResult.error("原密码不正确");
            }
            return HttpResult.ok(sysUserService.changePassword(passwordVo.getPassword(),userId));

        }

        return HttpResult.error("密码修改失败");
    }





}
