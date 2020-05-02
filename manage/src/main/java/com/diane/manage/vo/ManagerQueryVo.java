package com.diane.manage.vo;

import lombok.Data;

@Data
public class ManagerQueryVo {
    private String beginTime;
    private String endTime;
    private Long shopId;
    private Long companyId;
    private Integer pageNum;
    private Integer pageSize;
    private Long regionId;
    private Integer orderStatus;
    private Integer orderType;
}
