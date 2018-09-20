package com.casic.oarp.datavisual.model;

import java.io.Serializable;

public enum  ResultCode implements Serializable {

    SUCCESS("000000", "success"),
    /*****************************************************通用系统错误******************************************/
    INVALID_TOKEN("100001", "Invalid Token!"),
    INTERNAL_ERROR("100002", "Internal Error!"),
    INVALID_PARAMS("100003", "Invalid Params!");

    private String code;
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
