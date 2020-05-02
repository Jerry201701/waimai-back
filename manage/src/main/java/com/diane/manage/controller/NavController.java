package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.manage.model.NavInfo;
import com.diane.manage.service.NavInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class NavController {
    @Autowired
    private NavInfoService navInfoService;
    @PostMapping(value = "/nav/save")
    public HttpResult addNavInfo(@RequestBody NavInfo navInfo){
        return HttpResult.ok(navInfoService.editNavInfo(navInfo));
    }
    @PostMapping(value = "/nav/find/page")
    public  HttpResult findPageNav(PageRequest pageRequest){
        return HttpResult.ok(navInfoService.findPage(pageRequest));
    }
    @PostMapping(value = "/nav/delete")
    public  HttpResult deleteNavInfo(@RequestBody List<NavInfo> list){
        return HttpResult.ok(navInfoService.deleteNavInfo(list));
    }


}
