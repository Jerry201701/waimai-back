package com.diane.manage.vo.activity;

import lombok.Data;

@Data
public class FullReductionVo {
    private Integer type;
    private Long shopId;
    private String token;
    private Integer price;
    private Integer limitPrice;
}
