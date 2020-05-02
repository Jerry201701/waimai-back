package com.diane.manage.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class MarketActivity {
    private Long id;
    private String activityName;
    private Timestamp beginTime;
    private Timestamp endTime;
    private Long goodId;
    /*
    1秒杀，2团购，3特价，4砍价
     */
    private Integer type;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private Long createBy;
    private Long lastUpdateBy;
    private Boolean flag;
    private Long regionId;
    private Long companyId;
    private Long shopId;
    private String remark;
    private String companyName;
    private String shopName;
    private String goodName;
    /*
    秒杀活动状态
     */
    private Integer activityStatus;
    /*
    库存
     */
    private Integer stock;
    /*
    限购数量
     */
    private Integer restriction;
    /*
    活动价格
     */
    private Integer activityPrice;
    /*
    拼团最少人数
     */
    private Integer minPeople;
    /*
    拼团团员价格
     */
    private Integer memberPrice;
    /*
    拼团团长价格
     */
    private Integer leaderPrice;
    /*
    拼团有效期,精确到分钟
     */
    private Integer groupValidityTime;
    /*
    每次最多砍价金额最大值
     */
    private Integer maxBargainPerTime;
    /*
    每次最小砍价金额
     */
    private Integer minBargainPerTime;

    /*
    砍价最小付款价
     */
    private Integer minBargainPayment;

    /*
    砍价有效期，精确到分钟
     */
    private Integer bargainValidity;
    /*
    是否允许用户自己砍价
     */
    private Boolean bargainSelf;
    /*
    最多砍价次数
     */
    private Integer bargainTimeLimit;


}
