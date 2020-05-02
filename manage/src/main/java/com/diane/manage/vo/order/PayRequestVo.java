package com.diane.manage.vo.order;

import lombok.Data;

@Data
public class PayRequestVo {
        private  String command;
        private String paymentway;
        private String  platformid;
        private String merid;
        private String mernum;
        private String appid;
        private String  sign;
        private String local_order_no;

}
