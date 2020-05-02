package com.diane.manage.vo;

import lombok.Data;

@Data
public class PasswordVo {
  private String  token;
  private  Long   companyId;
  private Long  deliveryId;
  private Long    shopId;
  /*
  新的登录密码
   */
  private String  password;
  /*
  旧的登录密码
   */
  private String    oldPassword;
  /*
  新的操作密码
   */
 private String operatorPassword;
  /*
  旧的操作密码
   */
  private String oldOperatorPassword;
  /*
  1,修改登录密码
  2，修改操作密码
   */
  private Integer  type;
  private String code;
  private String phone;
  private Long userId;
  /*
  1，商户 2，门店管理员 3，配送员
   */
  private Integer userType;

}
