package com.diane.manage.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class ShopWithdraw {
    private Long withdrawId;
    /*
    0全部，1,已完成，2：审核中，3，等待打款，4失败5 成功
     */
    private Integer status;
    private Integer money;
    private Timestamp applyTime;
    private Long companyId;
    private Integer type;
    private String question;
    private Long deliveryId;
    private String openid;
    private String reason;

}
