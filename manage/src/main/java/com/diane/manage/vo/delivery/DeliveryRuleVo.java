package com.diane.manage.vo.delivery;

import lombok.Data;

@Data
public class DeliveryRuleVo {
    /*
    1,固定 0比例
     */
    private Boolean type;
    /*
    固定值
     */
    private Integer fixValue;
    /*
    比例值
     */
    private Integer rateValue;
    /*
    最大值
     */
    private Integer maxValue;
    /*
    最小值
     */
    private Integer minValue;


}
