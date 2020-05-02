package com.diane.manage.vo.delivery;

import lombok.Data;


@Data
public class DeliveryQueryVo {
    private Long deliveryId;
    /*
    订单状态:1,待支付，
    2，已支付，
    3，取消订单退款成功，
    4，取消订单退款失败
     5，无效订单退款
     6，部分退款成功
     7，部分退款失败；
     */
    private Integer orderStatus;
    /*
    1店铺订单、2跑腿订单
     */
    private Integer type;
    private Integer pageSize;
    private Integer pageNum;
    private String orderNumber;
    private Long regionId;
    /*
    配送状态
    1,配送员抢单成功
    2，配送中
    3，配送成功
    4，配送失败
    5，商户确认配送完成
     */
    private Integer status;
    private  Integer deliveryStatus;
    private Integer payStatus;
    private Integer handleStatus;
    private Boolean appointment;
    private String keyWord;
    private String dateTime;



}
