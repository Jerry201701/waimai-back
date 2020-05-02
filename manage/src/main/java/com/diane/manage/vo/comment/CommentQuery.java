package com.diane.manage.vo.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentQuery {
    private Long shopId;
    private String nickName;
    private Timestamp messageTime;
    private Integer messageType;
    private Integer pageNum;
    private Integer pageSize;
    private Long commentId;
    private String replyContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp endTime;
    private Long companyId;

}
