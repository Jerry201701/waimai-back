package com.diane.manage.model;

import com.diane.manage.vo.OrderGoodQuery;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ShopCart {
    private Long cartId;
    private String openid;
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
    private Boolean flag;
    private Long goodId;
    private Long labelId;
    /*
    1,加数量
    2，减数量
    3，清空
     */
    private Integer type;
    private  List<OrderGoodQuery> goodList;
    private Integer quantity;
    private Long shopId;

}
