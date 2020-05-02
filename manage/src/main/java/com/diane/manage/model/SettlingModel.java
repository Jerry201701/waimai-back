package com.diane.manage.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class SettlingModel {
    private Long id;
    private Long regionId;
    /*
    固定金额
     */
    private BigDecimal fixedAmount;
    /*
    比例
     */
    private Integer settlingRate;
    /*
    封顶值
     */
    private Integer upperValue;
    /*
    最小值
     */
    private Integer bottomValue;
    private Long companyId;
    private Long shopId;
    private Long goodId;
    /*
    配送设置的id
     */
    private List<String> unitId;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private Boolean flag;
    /*
    按照结算方式：0：按订单，1：按商品
     */
    private Boolean settlingWay;
    /*
    结算规则：0：固定金额，1：按比例
     */
    private Boolean calculationRule;
    /*
    平台提成
     */
    private Integer percentage;
    /*
    手续费
     */
    private Integer fee;


}
