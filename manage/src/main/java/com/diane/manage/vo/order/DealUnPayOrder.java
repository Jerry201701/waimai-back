package com.diane.manage.vo.order;

import lombok.Data;

@Data
public class DealUnPayOrder {
    private String command;
    private String  orderNum;
    private Integer payWay;
    private String  subAppId;
    private String  openid;

}
