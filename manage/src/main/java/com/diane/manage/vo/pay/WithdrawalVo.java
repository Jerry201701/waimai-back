package com.diane.manage.vo.pay;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawalVo {
    private Long companyId;
    private BigDecimal money;
}
