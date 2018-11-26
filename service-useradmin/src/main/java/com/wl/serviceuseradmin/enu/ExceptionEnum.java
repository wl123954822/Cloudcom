package com.wl.serviceuseradmin.enu;

public enum ExceptionEnum {
    USERNAME_NULL("000","用户名为空"),
    PASSWORD_NULL("001","密码为空");

    private String code;

    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
