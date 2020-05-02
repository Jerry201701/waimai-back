package com.diane.manage.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AddressQuery {
    /*
    地址编号
     */
    private Long addressId;
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

    private  String openid;

    private String addressLabel;
    /*
    联系人姓名
     */
    private String contact;
    /*
    配送地址
     */
    private String address;
    /*
    所属校区
     */
    private Long regionId;
    /*
    是否删除
     */
    private Boolean flag;
    /*
    是否设为默认地址
     */
    private Boolean setDefault;
    /*
    电话号码
     */
    private String phone;
    /*
    区域名称
     */
    private String regionName;
}
