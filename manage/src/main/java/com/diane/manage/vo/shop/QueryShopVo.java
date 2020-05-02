package com.diane.manage.vo.shop;

import lombok.Data;

@Data
public class QueryShopVo {
    private Integer pageNum;
    private Integer pageSize;
    private Long regionId;
    private Integer status;
    private Long companyId;
}
