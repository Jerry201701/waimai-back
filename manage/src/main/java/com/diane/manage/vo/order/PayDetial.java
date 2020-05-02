package com.diane.manage.vo.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayDetial {
    /*
    交易日期 trandate String √ yyyyMMdd

   */
    private String trandate;
    /*
      交易流水 transeqno String √

     */
  /*
   消费者手机端显示的订单编
    号，对应 dwOrderNo 中心平
            台订单号
   */
    private String dwOrderNo;
    /*

     实收金额 settamount 订单金额以该字段为准
             本地订单号
     */
    private BigDecimal settamount;
    /*

     local_order
             _no
     String √ 接入方的本地订单号
     */
    private String local_order_no;
    /*
             订单交易状态

     */
    private  String  status;


}
