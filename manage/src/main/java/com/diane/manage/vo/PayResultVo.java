package com.diane.manage.vo;

import lombok.Data;

@Data
public class PayResultVo {
    private String timeStamp;
    private String nonceStr;
    private String prepay_id;
    private String pay_sign;
    private String orderno;
    private String balance;
}
