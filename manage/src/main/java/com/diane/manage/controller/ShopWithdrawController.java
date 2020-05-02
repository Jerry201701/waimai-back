package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.manage.service.ShopWithdrawService;
import com.diane.manage.vo.withdrawal.WithdrawalRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/withdraw")
public class ShopWithdrawController {
    @Autowired
    private ShopWithdrawService shopWithdrawService;
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(shopWithdrawService.findPage(pageRequest));
    }
    @PostMapping(value = "/edit")
    public HttpResult editWithdraw(@RequestBody WithdrawalRespVo withdrawalRespVo){



        return HttpResult.ok(shopWithdrawService.checkWithdraw(withdrawalRespVo.getStatus(),withdrawalRespVo.getWithdrawId()));
    }



}
