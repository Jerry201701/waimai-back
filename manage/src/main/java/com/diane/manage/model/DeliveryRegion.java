package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DeliveryRegion {
    /*
    区域编号
     */
    private Long id;
    /*
    区域名称
     */
    private String regionName;
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
    逻辑删除标志
     */
    private Boolean flag;
    /*
    备注
     */
    private String remark;
}
