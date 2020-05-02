package com.diane.manage.vo.activity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class AssembleActivityRespVo {
    private Long assembleId;
    private List<String> userPicUrl;
    private String nickName;
    private Timestamp endTime;
    private Integer leftCount;
    private Integer minPeople;
    private Integer groupValidityTime;
    private Integer assembleTimes;
    private Timestamp startTime;

}
