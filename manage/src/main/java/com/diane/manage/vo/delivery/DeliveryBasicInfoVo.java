package com.diane.manage.vo.delivery;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeliveryBasicInfoVo {
    /*
    1开工,0停工,2未入驻学校 ,  3审核中
     */
    private Integer status;
    /*
    区域id
     */
    private Long regionId;
    /*
    配送员名称
     */
    private String  deliveryName;
    /*
    可提现佣金
     */
    private BigDecimal withdrawableAmount;
    /*
    今日已抢
     */
    private Integer   grabNumber;
    /*
    今日完成
     */
    private Integer finishOrder;
    /*
    今日失败
     */
     private Integer   fail;


}
