package com.diane.manage.vo.activity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class ActivityConditionVo {
    /*
    已经砍价的次数
     */
    private Integer bargainTimes;
    /*
    砍价次数限制
     */
    private Integer bargainTimeLimit;
    /*
    每次最多砍价金额
     */
    private Integer bargainLimit;
    /*
    每次最少砍价金额
     */
    private Integer minBargainLimit;
    /*
    砍价最少付款金额
     */
    private Integer minBargainPayment;
    /*
    openid
     */
    private String openid;
    /*
    砍价id
     */
    private Long bargainId;
    /*
    活动id
     */
    private Long activityId;
    /*
    商品id
     */
    private Long goodId;
    /*
    砍价有效期
     */
    private Integer bargainValidity;
    /*
    团长价
     */
    private Integer leaderPrice;
    /*
    团员价
     */
    private Integer memberPrice;
    /*
    拼团有效期
     */
    private Integer assembleValidity;
    /*
    拼团最少人数
     */
    private Integer minPeople;
    /*
    砍价的当前价格
     */
    private Integer bargainCurrentPrice;
    /*
    发起砍价的时间
     */
    private Timestamp bargainTime;



}
