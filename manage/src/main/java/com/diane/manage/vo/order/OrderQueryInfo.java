package com.diane.manage.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderQueryInfo {
    private String openid;
    /*
    用来筛选订单：1，待付款 2，待收货 3，已完成 4，待评价 ,5,配送完成商户未确认的
     */
    private Integer status;
    private Long orderId;
    private Long shopId;
    private Timestamp orderTime;
    private String keyWord;
    private Long companyId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp endTime;
    private Boolean appointment;
    private Integer payStatus;
    private Integer handleStatus;
    private Integer deliveryStatus;
    private Integer pageNum;
    private Integer pageSize;

}
