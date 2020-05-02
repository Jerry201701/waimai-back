package com.diane.manage.vo.order;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class PayResultDetail {
    private String localOrderNo;
    private String dwOrderNo;
    private String outOrderNo;
    private BigDecimal totalAmount;
    private BigDecimal refundAmount;
    private Timestamp refundTime;
    private BigDecimal receiveAmount;
    private Integer paymentway;
    private String sign;
    private Timestamp timestamp;
    private String subject;
    private Timestamp payTime;
    private String orderStatus;


}
