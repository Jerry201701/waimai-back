package com.diane.manage.vo;

import lombok.Data;

@Data
public class DecodePhoneInfo {
    private String encryptedData;
    private String iv;
    private String sessionKey;
    private String code;
    private Integer type;
}
