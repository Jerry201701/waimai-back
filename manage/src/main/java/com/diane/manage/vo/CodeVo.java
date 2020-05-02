package com.diane.manage.vo;

import lombok.Data;

@Data
public class CodeVo {
    private String scene;
    private String page;
    private Integer width;

    @Override
    public String toString() {
        return "{" +
                "scene='" + scene + '\'' +
                ", page='" + page + '\'' +
                ", width=" + width +
                '}';
    }
}
