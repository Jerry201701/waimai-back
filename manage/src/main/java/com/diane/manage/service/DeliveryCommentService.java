package com.diane.manage.service;

import com.diane.manage.model.DeliveryComment;
import com.diane.manage.vo.comment.CommentPageResultVo;
import com.diane.manage.vo.comment.CommentQuery;
import com.diane.manage.vo.comment.CommentRespVo;
import com.github.pagehelper.Page;

public interface DeliveryCommentService {
    Long saveComment(DeliveryComment deliveryComment);

    Page<CommentPageResultVo> findPage(Long shopId,Integer commentType);



    CommentRespVo  shopCommentCount(Long shopId);
    int commentReply(CommentQuery commentQuery);
    Page<CommentPageResultVo> shopCommentPageInfo(CommentQuery commentQuery);



}
