package com.diane.manage.vo.order;

import lombok.Data;


@Data
public class CountOrderVo {
    /*
    待付款订单总数
     */
    private Integer waitpay;
    /*
    待收货
     */

    private Integer waitreceive;
    /*
    待评价
     */

    private Integer waitcomment;
}