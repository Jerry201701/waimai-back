package com.diane.manage.controller;

import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import com.diane.manage.model.DeliveryComment;
import com.diane.manage.service.DeliveryCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer/shop/comment")
public class DeliveryCommentController {
    /*
    @Autowired
    private DeliveryCommentService deliveryCommentService;
    @RequestMapping(value = "/add")
    public HttpResult addDeliveryComment(@RequestBody DeliveryComment deliveryComment){
        if (deliveryComment.getId()==null||deliveryComment.getId()==0){
        return HttpResult.ok(deliveryCommentService.saveComment(deliveryComment));

        }
        return HttpResult.ok(deliveryCommentService.updateComment(deliveryComment));

    }

    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(deliveryCommentService.findPage(pageRequest));
    }
    @GetMapping(value = "/detail")
    public HttpResult showDetail(@RequestParam(value = "id")Long id){
        return HttpResult.ok(deliveryCommentService.showCommentDetailById(id));
    }



     */

}
