package com.gladfish.frame.exception;

import com.gladfish.common.enums.EnumErrorCode;

public class BizException extends Exception{

    private String code;

    public BizException(String message) {
        super(message);
    }

    public BizException(String code,String message) {
        super(message);
        this.code = code;
    }

    public BizException(EnumErrorCode enumErrorCode) {
        super(enumErrorCode.getName());
        this.code = enumErrorCode.getCode();
    }

    public String getCode() {
        return code;
    }
}