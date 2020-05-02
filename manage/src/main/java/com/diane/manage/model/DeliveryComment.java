package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DeliveryComment {
    /*
    id
     */
    private Long id;
    /*
    送达速度
     */
    private Integer speed;
    /*
    服务态度
     */
    private Integer attitude;
    /*
    是否匿名
     */
    private Boolean anonymous;
    /*
    小程序用户标识
     */
    private String openid;
    /*
    订单编号
     */
    private String orderNumber;
    /*
    是否删除
     */
    private Boolean flag;
    /*
    评论内容
     */
    private String comment;
    /*
    门店编号
     */
    private Long shopId;

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
    好评
     */
    private Boolean praise;
    /*
    中评
     */
    private Boolean middle;
    /*
    差评
     */
    private Boolean negative;
}
