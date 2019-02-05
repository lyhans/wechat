package com.gladfish.common.enums;

import com.gladfish.work.wechat.enums.EnumWechatCode;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/2/4 17:43
 */
public enum EnumErrorCode {
    UNSUBSCRIBE("用户未关注","G_0001"),

    ;
    // 提示信息
    private String name;
    // 返回码
    private String code;

    /**
     * @param {name：提示信息}
     * @param {value：全局返回码}
     * @description {Constructor with parameters}
     * @description {有参构造函数}
     */
    EnumErrorCode(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "EnumWechatCode{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }

    public static EnumErrorCode getEnum(String code) {
        for (EnumErrorCode codeEnum : EnumErrorCode.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum;
            }
        }
        return null;
    }
}
