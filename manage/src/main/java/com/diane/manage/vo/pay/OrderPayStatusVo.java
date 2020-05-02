package com.diane.manage.vo.pay;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OrderPayStatusVo {
    private String localOrderNo;
    private String dwOrderNo;
    private String outOrderNo;
    private String orderStatus;
    private BigDecimal totalAmount;
    private BigDecimal refundAmount;
    private Timestamp refundTime;
    private BigDecimal receiveAmount;
    private String paymentway;
    private String sign;
    private Timestamp timestamp;
    private String subject;
    private Timestamp payTime;
}
