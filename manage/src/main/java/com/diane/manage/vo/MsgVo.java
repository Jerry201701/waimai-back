package com.diane.manage.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MsgVo  {
    private Long  id;
    private Integer type;
    private Integer count;

    public MsgVo(Long id, Integer type, Integer count) {
        this.id = id;
        this.type = type;
        this.count = count;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", type=" + type +
                ", count=" + count +
                '}';
    }
}
