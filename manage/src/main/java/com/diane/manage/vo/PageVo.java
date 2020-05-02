package com.diane.manage.vo;

import lombok.Data;

@Data
public class PageVo {
    private Integer pageNum;
    private Integer pageSize;
    private String openid;
    private Long shopId;
}
