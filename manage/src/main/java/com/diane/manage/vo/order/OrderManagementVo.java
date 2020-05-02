package com.diane.manage.vo.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderManagementVo {
    /*
    1,接单
    2，无效订单
    3，确认订单完成
     */
    private Integer status;
    private Long shopId;
    private BigDecimal refundAmount;
    private String orderNumber;
    private String openid;
    /*
    1取消订单
    2，催单
    3，申请退款
     */
    private Integer type;
    private Integer handleStatus;
}
