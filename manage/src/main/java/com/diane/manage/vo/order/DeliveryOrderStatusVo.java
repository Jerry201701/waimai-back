package com.diane.manage.vo.order;

import lombok.Data;

@Data
public class DeliveryOrderStatusVo {
    private Integer status;
    private Integer amount;
    private Boolean autoReceipt;

}
