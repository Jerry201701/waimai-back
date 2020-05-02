package com.diane.manage.vo.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderPayStatusVo {
    private String result;
    private  String msg;
    private String dwOrderNo;
    private BigDecimal receipt_amount;
    private String body;

    public OrderPayStatusVo(String result, String msg) {
        this.result = result;
        this.msg = msg;
    }
}
