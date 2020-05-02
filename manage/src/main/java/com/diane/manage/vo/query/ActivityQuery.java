package com.diane.manage.vo.query;

import lombok.Data;

@Data
public class ActivityQuery {
    private String openid;
    private Integer pageSize;
    private Integer pageNum;
    private Long goodId;
    private Long bargainId;


}
