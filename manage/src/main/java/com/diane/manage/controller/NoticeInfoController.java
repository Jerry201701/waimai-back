package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.manage.model.NoticeInfo;
import com.diane.manage.service.NoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeInfoController {
    @Autowired
    private NoticeInfoService noticeInfoService;

    @PostMapping(value = "/add")
    public HttpResult saveAdvertisement(@RequestBody NoticeInfo noticeInfo){
        if (noticeInfo.getId()==null||noticeInfo.getId()==0){
            noticeInfo.setFlag(true);
            return HttpResult.ok(noticeInfoService.saveNoticeInfo(noticeInfo));
        }
        return HttpResult.ok(noticeInfoService.updateNoticeInfo(noticeInfo));
    }

   // @PreAuthorize("hasAuthority('manage:company:view')")
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(noticeInfoService.findPage(pageRequest));
    }


    @PostMapping(value = "/delete")
    public HttpResult deleteCompany(@RequestBody List<NoticeInfo> noticeInfos){
        return HttpResult.ok(noticeInfoService.deleteNoticeInfo(noticeInfos));
    }






}
