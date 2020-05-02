package com.diane.manage.vo.shop;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CompanyBasicInfoVo {
    private Long companyId;
    private String merName;
    private String bossName;
    private String merPicUrl;
    private List<Long> shops;
    private List<ShopBasicInfoRespVo> shopBasicInfo;
    /*
    账户余额
     */
   // private BigDecimal balance;
    private Integer balance;
    /*
    今日营业额
     */
    private Integer become;
    /*
    今日订单数
     */
    private Integer orderNumber;
    /*
    未处理订单数
     */
    private Integer unhandle;

    /*
    店铺数量
     */
    private Integer storeNumber;
    /*
    商品数量
     */
    private Integer goodNumber;
    /*
    可提现余额
     */
   // private BigDecimal withdrawAmount;
    private Integer withdrawAmount;





}
