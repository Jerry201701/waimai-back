package com.diane.manage.vo.comment;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class CommentPageResultVo {
    /*
  评论标识
   */
    private Long commentId;
    /*
    评论内容
     */
    private String replyContent;
    /*
    商家回复内容
     */
    private String reply;
    /*
    评论者头像地址
     */
    private String customerPicUrl;
    /*
    评论时间
     */
    private Timestamp commentTime;
    /*
    评论者昵称
     */
    private String customerName;
    /*
    订单评星
     */
    private Integer orderLevel;
    /*

     */
    private Integer attitude;
    private String commentContent;
    private String nickName;
    private String headUrl;
    private Integer praise;


}
