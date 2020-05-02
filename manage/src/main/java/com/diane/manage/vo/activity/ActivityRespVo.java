package com.diane.manage.vo.activity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class ActivityRespVo {
    private Long activityId;
    private Long regionId;
    private Long  companyId;
    private Long shopId;
    private Long goodId;
    private String regionName;
    private String companyName;
    private String shopName;
    private String goodName;
    private Integer promotionType;
    private Integer promotionPrice;
    private String path;
    private List<String> goodPic;
    private Integer price;
    private Integer validTime;
    private Integer createPrice;
    private Integer joinPrice;

    private Integer bargainLeftTime;
    /*
    砍价的底线
     */
    private Integer bargainBottomPrice;
  //  private Integer stock;
    private Integer promotionStock;
    private Boolean bargainSelf;
    /*
    活动结束时间
     */
    private Timestamp secKillEndTime;
    private Integer minBargainTime;
    private Integer maxBargainTime;



}
