package com.wl.serviceaddress.common;

/**
 * @author wanglei
 * 枚举类
 */
public enum ResultEnum {
    /**
     * 枚举
     */
    SUCCESS(0,"成功"),
    NO_LOGIN_ERROR(1,"尚未登录，请登录！"),
    PARAM_ISNULL(3,"传参为空"),
    RESULT_REPEAT(4,"结果重复"),
    NO_JURISDICTION(5,"没有权限"),
    LOGIN_OUT_SUCCESS(6,"退出成功"),
    ERROR(7,"失败");
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
