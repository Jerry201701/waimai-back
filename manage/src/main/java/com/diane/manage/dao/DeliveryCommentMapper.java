package com.diane.manage.dao;

import com.diane.manage.model.DeliveryComment;
import com.diane.manage.vo.comment.CommentPageResultVo;
import com.diane.manage.vo.comment.CommentQuery;
import com.diane.manage.vo.comment.CommentRespVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Mapper
public interface DeliveryCommentMapper {

    Page<CommentPageResultVo> findPage(@Param(value = "shopId") Long shopId,@Param(value = "commentType")Integer commentType);

    int insertSelective(DeliveryComment deliveryComment);


    CommentRespVo countCommentByShop(@Param(value = "shopId") Long shopId);

    List<DeliveryComment> listCommentDetailByShop(@Param(value = "shopId") Long shopId);

    int commentReply(CommentQuery commentQuery);
    Page<CommentPageResultVo> shopCommentPageInfo(CommentQuery commentQuery);





}
