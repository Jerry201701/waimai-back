package com.diane.manage.vo.good;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CopyGoodVo {
    private Long sourceGoodId;
    private Long targetGoodId;
    private Boolean flag;
    private Timestamp createTime;
}
