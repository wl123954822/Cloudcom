package com.wl.serviceuseradmin.enu;

public enum DataEnum {
    超级管理员("ROLE_admin");

    private String desc;

    private DataEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
