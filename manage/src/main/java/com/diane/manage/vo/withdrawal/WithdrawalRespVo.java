package com.diane.manage.vo.withdrawal;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WithdrawalRespVo {
    private Long withdrawId;
    /*
    0全部，1,已完成，2：审核中，3，等待打款，4失败5 成功
     */
    private Integer status;
    private Integer withdrawAmount;
    private Timestamp applyTime;
    private Long companyId;
    /*
    1,商户 2，配送员
     */
    private Integer type;
    private String question;
    private Long deliveryId;
    private String reason;
    private  String companyName;
    private String deliveryName;
    private String accountName;

}
