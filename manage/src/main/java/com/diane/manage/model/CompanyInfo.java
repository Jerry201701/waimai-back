package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CompanyInfo {
    /*
    商户编号
     */
    private Long id;
    /*
    老板名称
     */
    private  String bossName;
    /*
    联系方式
     */
    private String phone;
    /*
    地址
     */
    private  String address;
    /*
    商户名称
     */
    private  String companyName;

    /*
    区域id
     */
    private Long regionId;
    /*
    是否删除
     */
    private  Boolean flag;
    /*
    用户标识
     */
    private Long userId;
    /*
    创建时间
     */
    private Timestamp createTime;
    /*
    修改时间
     */
    private Timestamp lastUpdateTime;
    /*
    创建人
     */
    private Long createBy;
    /*
    修改人
     */
    private Long lastUpdateBy;
    /*
    备注
     */
    private String remark;
    /*
    照片数组
     */
    private List<PictureInfo> pictures;
    /*
    微信用户标识
     */
    private String openid;
    /*
    操作密码
     */
    private String operatePassword;

    /*
    注册用户名称
     */
    private String roleName;


}
