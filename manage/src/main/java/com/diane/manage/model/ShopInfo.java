package com.diane.manage.model;

import com.diane.manage.vo.activity.FullReductionVo;
import com.diane.manage.vo.delivery.DeliveryRuleVo;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ShopInfo {
    /*
    门店编号
     */
    private  Long id;
    /*
    店铺名称
     */
    private String shopName;
    /*
    店铺地址
     */
    private String address;
    /*
    店铺电话
     */
    private String phone;
    /*
    服务范围
     */
    private Integer deliveryRange;
    /*
    起送价
     */
    private Integer basePrice;
    /*
    未满起送价加收费
     */
    private Integer extraPrice;

    /*
    配送区域描述
     */
    private String deliveryRegionDesc;
    /*
    店铺描述
     */
    private String shopDesc;
    /*
    库存功能开关
     */
    private Boolean inventory;
    /*
    显示库存
     */
    private Boolean displayInventory;
    /*
    每日清算库存
     */
    private Boolean dailyInventory;
    /*
    营业开始时间
     */
    private String openTime;
    /*
    营业结束时间
     */
    private String closeTime;
    /*
    进店是否验证位置
     */
    private Boolean verified;

    /*
    是否逻辑删除
     */
    private Boolean flag;
    /*
    是否打烊
     */
    private Boolean closed;
    /*
    是否在商户显示
     */
    private Boolean display;
    /*
    是否自动接单
     */
    private Boolean autoReceipt;
    /*
    所属商户编号
     */
    private Long companyId;
    /*
    所在区域
     */
    private Long regionId;
    /*
    门店分类
     */
    private Long categoryId;
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
    封面图
     */
    private List<PictureInfo> pictures;
    /*
    是否启用
    1开业
    2，歇业且隐藏
    5，歇业且显示
    3审核中
    4审核失败shop_status

     */
    private Integer shopStatus;
    /*
    分类标签
     */
    private List<CategoryInfo> categoryList;
    /*
    门店评论
     */
    private List<DeliveryComment> commentList;
    /*
    区域名称
     */
    private String regionName;
    /*
    餐盒费
     */
    private Integer packCharges;
    /*
    门店图片
     */
    private List<PictureInfo> pictureInfoList;
    /*
    活动是否重叠
     */
    private Boolean overlay;
    /*
    歇业提示语
     */
    private String content;
    private Boolean pass;
    /*
    是否收藏
     */
    private Boolean shopFav;
    /*
    二维码地址
     */
    private String codeUrl;
    /*
    配送费
     */
    private DeliveryRuleVo deliveryFee;
    /*
    满减活动列表
     */
    private List<FullReductionVo>fullReduction;
    /*
    查询关键词
     */
    private String keyWord;



}
