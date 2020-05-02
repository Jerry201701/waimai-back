package com.diane.manage.vo;

import lombok.Data;

@Data
public class OrderGoodQuery {
    private Long goodId;
    private Integer quantity;
    private Long  labelId;
}
