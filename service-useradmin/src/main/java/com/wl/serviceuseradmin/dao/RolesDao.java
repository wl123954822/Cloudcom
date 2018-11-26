package com.wl.serviceuseradmin.dao;


import com.wl.serviceuseradmin.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolesDao {

    /**
     * 创建角色权限
     * @param username
     * @param password
     * @return
     */
    int addRolesByAdmin(@Param("name") String username, @Param("nameZh") String password);

    /**
     * 通过namec查询是否存在role
     * @param name
     * @return
     */
    Role loadRolesByName(@Param("name") String name);

    /**
     * 删除角色权限
     * @param id
     * @return
     */
    int deleteRoles(@Param("id") Long id);

    List<Role> showRolesList(@Param("id") Long id);

}
