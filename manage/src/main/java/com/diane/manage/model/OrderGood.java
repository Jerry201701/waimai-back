package com.diane.manage.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderGood {
    /*
    订单id
     */
    private Long orderId;
    /*
    商品id
     */
    private Long goodId;
    /*
    标签id
     */
    private Long labelId;
    /*
    数量
     */
    private  Integer quantity;
    /*
    订单编号
     */
    private String orderNumber;
    /*
    商品名称
     */
    private String goodName;
    /*
    商品标签名称
     */
    private String labelName;
    /*
    商品图片地址
     */
    private List<String> goodPic;
    /*
    商品价格
     */
    private Integer price;
    /*
    创建时间
     */
    private Timestamp createTime;
}
