package com.wl.serviceuseradmin.enu;

/**
 * @author wanglei
 *
 */
public enum ResultEnum {

    SUCCESS(0,"成功"),
    NO_LOGIN_ERROR(1,"尚未登录，请登录！"),
    PARAM_ISNULL(3,"传参为空"),
    RESULT_REPEAT(4,"结果重复"),
    NO_JURISDICTION(5,"没有权限"),
    LOGIN_OUT_SUCCESS(6,"退出成功"),
    LOGIN_FAIL(7,"登录失败"),
    ROLE_ERROR(8,"角色错误"),
    PASSWORD_ERROR(9,"密码错误");
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
