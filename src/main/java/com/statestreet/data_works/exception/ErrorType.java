package com.statestreet.data_works.exception;

import lombok.Getter;

public enum ErrorType {
    SUCCESS("200", "SUCCESS"),
    SYSTEM_ERROR("100000", "SYSTEM_ERROR");

    private final String code;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private final String msg;

    ErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
