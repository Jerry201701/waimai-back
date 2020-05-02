package com.diane.manage.constants;

public enum DeliveryStatusEnum {
    GRAB_SUCCESS(600,"抢单成功"),
    GRAB_FAIL(601,"抢单失败"),
    DELIVERY_FAIL(602,"配送失败"),
    DELIVERY_SUCCESS(603,"配送成功"),
    HANDLE_SUCCESS(604,"制作成功"),
    HANDLE_FAIL(605,"制作失败"),
    PAY_SUCCESS(606,"用户支付成功"),
    PAY_FAIL(607,"用户支付失败"),
    INVALID_SUCCESS(608,"无效订单修改成功"),
    INVALID_FAIL(609,"无效订单修改失败");

    private int code;
    private String status;
    private DeliveryStatusEnum(int code,String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
