package com.diane.manage.vo.activity;

import lombok.Data;

import java.util.List;

@Data
public class BargainGoodVo {
    private Long goodId;
    private List<String> goodPic;
    private Integer goodPrice;
    private String goodName;

}
