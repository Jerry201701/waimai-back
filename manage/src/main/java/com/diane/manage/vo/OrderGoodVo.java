package com.diane.manage.vo;

import com.diane.manage.model.GoodLabel;
import lombok.Data;

import java.util.List;

@Data
public class OrderGoodVo {
    private Long goodId;
    private Long quantity;
    private String goodName;
    private Integer goodPrice;
    private List<GoodLabel> labels;
}
