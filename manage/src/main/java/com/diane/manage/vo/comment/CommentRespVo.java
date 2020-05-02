package com.diane.manage.vo.comment;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class CommentRespVo {
    private Long shopId;
    private BigDecimal comprehensive;
    private BigDecimal speed;
    private BigDecimal attitude;
    private Integer praise;
    private Integer middle;
    private Integer negative;

}
