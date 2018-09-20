package com.casic.oarp.datavisual.exception;

import com.casic.oarp.datavisual.model.ResultCode;

public class ZXFKException extends Exception {

    private ResultCode resultCode;

    public ZXFKException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
