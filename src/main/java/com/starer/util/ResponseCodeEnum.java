package com.starer.util;

public enum ResponseCodeEnum {

    SUCCESS(300),
    ERROR(400);

    private Integer status;

    ResponseCodeEnum() {
    }

    ResponseCodeEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
