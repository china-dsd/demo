package com.casic.oarp.datavisual.model;

import java.io.Serializable;

public class RestResult<T> implements Serializable {

    protected T data;
    protected String code = ResultCode.SUCCESS.getCode();
    protected String message = ResultCode.SUCCESS.getMessage();

    public RestResult() {
    }

    public RestResult(T data) {
        this.data = data;
    }

    public RestResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "data=" + data +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
