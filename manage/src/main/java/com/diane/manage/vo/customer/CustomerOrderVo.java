package com.diane.manage.vo.customer;

import lombok.Data;

@Data
public class CustomerOrderVo {
    private String orderNumber;
    private String openid;
    /*
    1取消订单
    2，催单
    3，申请退款
     */
    private Integer type;

}
