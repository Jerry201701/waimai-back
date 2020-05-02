package com.diane.manage.vo.pay;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class RefundResultVo {
    /*
    退款状态
     */
    private String refundStatus;
    /*
    本地退款订单号
     */
    private String localOrderNo;
    /*
    订单号
     */
    private String dwOrderNo;
    /*
    支付宝，微信订单号
     */
    private String outOrderNo;
    /*
    时间戳
     */
    private Timestamp timestamp;
    /*
    签名值
     */
    private String sign;
    /*
    退款金额
     */
    private BigDecimal refundAmount;
    /*
    返回状态值
     */
    private Integer code;
}
