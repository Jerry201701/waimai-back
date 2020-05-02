package com.diane.manage.vo;

import lombok.Data;

@Data
public class GoodQuery {
    private Long goodId;
    private String token;
    private Long shopId;
    private String keyWord;
    private Long categoryId;
    /*
    0:全部 1，正常销售 2 售罄
     */
    private Integer status;
    private String salePlan;
    private Integer shelfStatus;
    private Integer companyId;

}
