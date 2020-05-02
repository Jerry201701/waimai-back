package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WeiUserInfo {
    private String nickName;
    private String openid;
    private Integer sex;
    private String  phone;
    private String headIcon;
    private String unionId;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private Long id;

}
