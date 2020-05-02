package com.diane.manage.vo.good;

import lombok.Data;

@Data
public class GoodQueryVo {
    private Long companyId;
    private Long regionId;
    private Long shopId;
    private Integer pageNum;
    private Integer pageSize;
}
