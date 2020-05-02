package com.diane.manage.vo.activity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CreateAssembleVo {
    private Long assembleId;
    private String  userPicUrl;
    private String nickName;
    private Timestamp endTime;
    private Integer leftCount;
    private Integer minPeople;
    private Integer groupValidityTime;
    private Timestamp startTime;

}
