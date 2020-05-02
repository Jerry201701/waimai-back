package com.diane.manage.vo.shop;

import lombok.Data;

@Data
public class RuleShopVo {
    private Long ruleId;
    private Long companyId;
    private Long shopId;
    private String shopName;
    private String companyName;
    private Long regionId;
    private Integer pageNum;
    private Integer pageSize;
}
