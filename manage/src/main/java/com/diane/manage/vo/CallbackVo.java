package com.diane.manage.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CallbackVo implements Serializable {
    private static final long serialVersionUID = 735814945334602403L;
    private String orderNumber;
    private String status;

    public CallbackVo(String orderNumber, String status) {
        this.orderNumber = orderNumber;
        this.status = status;
    }
}
