package com.diane.manage.service.impl;

import com.diane.manage.dao.DeliveryCommentMapper;
import com.diane.manage.model.DeliveryComment;
import com.diane.manage.service.DeliveryCommentService;
import com.diane.manage.vo.comment.CommentPageResultVo;
import com.diane.manage.vo.comment.CommentQuery;
import com.diane.manage.vo.comment.CommentRespVo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class DeliveryCommentServiceImpl implements DeliveryCommentService {
    @Autowired
    private DeliveryCommentMapper deliveryCommentMapper;
    @Override
    public Long saveComment(DeliveryComment deliveryComment) {
        deliveryComment.setCreateTime(new Timestamp(System.currentTimeMillis()));
       // System.out.println(deliveryComment.getCreateTime());
        deliveryCommentMapper.insertSelective(deliveryComment);


        return deliveryComment.getId();
    }


    @Override
    public Page<CommentPageResultVo> findPage(Long shopId,Integer commentType) {

        return deliveryCommentMapper.findPage(shopId,commentType);
    }




    @Override
    public CommentRespVo shopCommentCount(Long shopId) {
        if (deliveryCommentMapper.countCommentByShop(shopId)==null){
            return null;
        }
        CommentRespVo commentRespVo=deliveryCommentMapper.countCommentByShop(shopId);
        BigDecimal comprehensive=(commentRespVo.getAttitude().add(commentRespVo.getSpeed())).divide(new BigDecimal(2));
        commentRespVo.setComprehensive(comprehensive);

        return commentRespVo;
    }

    @Override
    public int commentReply(CommentQuery commentQuery) {
        return deliveryCommentMapper.commentReply(commentQuery);
    }

    @Override
    public Page<CommentPageResultVo> shopCommentPageInfo(CommentQuery commentQuery) {
        return deliveryCommentMapper.shopCommentPageInfo(commentQuery);
    }
}
