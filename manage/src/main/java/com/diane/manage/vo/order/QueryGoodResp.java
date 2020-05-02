package com.diane.manage.vo.order;

import lombok.Data;

import java.util.List;
@Data
public class QueryGoodResp {

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





}
