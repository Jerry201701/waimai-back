package com.diane.manage.vo.category;

import lombok.Data;

@Data
public class CopyCategoryVo {
    private Long sourceId;
    private Long targetId;
    private Long shopId;
}
