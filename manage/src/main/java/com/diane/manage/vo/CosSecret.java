package com.diane.manage.vo;

import lombok.Data;

@Data
public class CosSecret {
    private String tmpSecretId;
    private String tmpSecretKey;
    private String sessionToken;
    private Long expiredTime;
}
