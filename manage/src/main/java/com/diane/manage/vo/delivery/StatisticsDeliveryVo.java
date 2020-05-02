package com.diane.manage.vo.delivery;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatisticsDeliveryVo {
    private String deliveryTime;
    private Integer totalDeliveryNumber;
    private BigDecimal totalDeliveryFee;
    private Long deliveryId;
    private String deliveryDate;
    /*
    成交总额
     */
    private Integer allAmount;
    /*
    成功单数
     */
    private Integer successCount;
    /*
    失败单数
     */
    private Integer failCount;

}
