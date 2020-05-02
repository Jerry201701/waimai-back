package com.diane.manage.vo;

import lombok.Data;

@Data
public class RegisterBean {
    /*
    注册用户名实际是电话号码
     */
    private String name;
    /*
    密码
     */
    private String password;
    /*
    操作密码
     */
    private String operatePassword;
    /*
    注册类型
    1，商户 2，配送员
     */
    private Integer registerType;
    /*
    商户名称或者配送员名称
     */
    private String roleName;
    private Byte status;
    /*
    openid
     */
    private String openid;
}
