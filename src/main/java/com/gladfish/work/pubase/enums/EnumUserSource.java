package com.gladfish.work.pubase.enums;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/2/4 11:57
 */
public enum EnumUserSource {
    NORMAL(0,"后台注册"),
    WECHAT(1,"微信注册")
    ;

    EnumUserSource(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
