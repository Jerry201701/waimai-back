package com.diane.manage.vo.shop;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WithdrawVo {
    private Long companyId;
    private Integer status;
    private Integer pageNum;
    private Integer pageSize;
    private Long withdrawId;
    private Integer  withdrawAmount;
    private Timestamp applyTime;
    private String reason;
    private Long  deliveryId;




}
