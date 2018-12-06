package com.wl.serviceuseradmin.enu;

import jdk.nashorn.internal.parser.Token;

public enum DataEnum {
    超级管理员("ROLE_admin"),
    TOKEN("token"),
    OPENID("openid"),
    EXPIRE("expire"),
    BUYER("ROLE_buyer");
    private String desc;

    private DataEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
