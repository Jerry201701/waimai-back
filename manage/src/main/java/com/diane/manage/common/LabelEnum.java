package com.diane.manage.common;

public enum LabelEnum {
    OEGION01("地址标签",1);

    private  LabelEnum(String labelName, Integer labelValue) {
        this.labelName = labelName;
        this.labelValue = labelValue;
    }

    private String labelName;
    private Integer labelValue;

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Integer getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(Integer labelValue) {
        this.labelValue = labelValue;
    }
}
