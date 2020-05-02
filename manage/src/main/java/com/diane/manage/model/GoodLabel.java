package com.diane.manage.model;

import lombok.Data;

@Data
public class GoodLabel {
    private Long id;
    private String labelName;
    private Integer labelPrice;
    private Integer labelStock;
    private Long goodId;
    private Boolean flag;
}
