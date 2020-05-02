package com.diane.manage.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class NoticeInfo {
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private Long createBy;
    private Long lastUpdateBy;
    private Boolean flag;
    private Long  regionId;
    private String regionName;

}
