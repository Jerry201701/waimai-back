package com.diane.manage.vo.pay;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class QueryRefundResultVo {

   private String localOrderNo;
   private String dwOrderNo;
   private Timestamp creatTime;
   private String local_refund_no;
   private Timestamp refundTime;
   private String sign;
   /*
   退款成功：REFUND_SUCC
    */
   private String refundStatus;
   private Integer paymentWay;
   private BigDecimal refundAmount;




}
