package com.diane.manage.vo.activity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class BargainActivityRespVo {
    private Integer currentPrice;
    /*
    1进行中2已结束
     */
    private Integer status;
    /*
    截至时间
     */
    private Timestamp endTime;
    /*
    发起时间
     */
    private Timestamp createTime;
    private Long goodId;
    private List<String> goodPic;
    private Integer goodPrice;
    private String goodName;
    private Long activityId;
    private Long id;
    private Integer validityTime;


}
