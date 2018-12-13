package com.wl.entityvo;

/**
 * 用户-权限 -- 关联
 */
public class UserRole {

    private Long id;

    private Long userid;

    private Long rid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
}
