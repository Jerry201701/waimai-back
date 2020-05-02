package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Delivery {
    private Long deliveryId;
    private Long userId;
    private String deliveryName;
    /*
   是否删除
    */
    private  Boolean flag;
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
    骑手状态
    1:停工
    2:开工
    3:未入驻学校
    4，审核中
    5,审核失败
     */
    private Integer status;
    /*
    备注
     */
    private String remark;
    /*
    校区编号
     */
    private Long regionId;
    /*
    骑手联系地址
     */
    private String deliveryAddress;
    /*
    可提现佣金
     */
    private Integer withdrawableAmount;
    /*
    今日已抢订单数
     */
    private Integer grabNumber;
    /*
    今日完成订单数
     */
    private Integer finishOrder;
    /*
    今日失败订单数
     */
    private Integer fail;
    /*
    审核是否通过
     */
    private Boolean pass;
    /*
    联系电话
     */
    private String deliveryPhone;
}
