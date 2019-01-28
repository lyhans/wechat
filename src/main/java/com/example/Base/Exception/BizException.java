package com.example.base.exception;

public class BizException extends Exception{

    private String code;

    public BizException(String message) {
        super(message);
    }

    public BizException(String code,String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}