package com.diane.manage.vo;

import lombok.Data;

@Data
public class PwdCheckVo {
    private Long userId;
    private Long companyId;
    private Long adminId;
    private Long deliveryId;
    private String salt;
    private String password;
    private String adminOperatorPassword;
    private String companyOperatorPassword;
}
