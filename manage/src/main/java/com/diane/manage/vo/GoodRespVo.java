package com.diane.manage.vo;

import com.diane.manage.model.GoodLabel;
import com.diane.manage.model.PictureInfo;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class GoodRespVo {
    /*
   表id
    */
    private Long id;
    /*
    商品名称
     */
    private String goodName;
    /*
    商品单价
     */
    private Integer price;
    /*
    商品单位
     */
    private String unit;
    /*
    库存
     */
    private Integer stock;
    /*
    商品特性
     */
    private String feature;
    /*
    所属门店id
     */
    private Long shopId;
    /*
    上架计划
     */
    private String salePlan;
    /*
    是否上架
     */
    private Boolean onShelf;
    /*
    每日自动复原功能
     */
    private Boolean restore;

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
    是否逻辑删除
     */
    private Boolean flag;
    private List<GoodLabel> labels;
    /*
    商品图片
     */
    private List<PictureInfo> goodPics;
    private Long categoryId;
    /*
    商品特价
     */
    private Integer salePrice;
    /*
    种类名称
     */
    private String  categoryName;
    /*
    门店名称
     */
    private String shopName;
    /*
    是否启用库存
     */
    private Boolean inventory;
}
