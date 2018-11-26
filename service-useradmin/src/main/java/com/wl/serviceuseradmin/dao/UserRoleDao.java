package com.wl.serviceuseradmin.dao;


import com.wl.serviceuseradmin.entity.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * 用户-权限 --关联表
 */
public interface UserRoleDao {

    int deleteUserRoleByRid(@Param("rid") Long rid);

    int giveRoles(@Param("userid") Long userId, @Param("rid") Long rId);

    UserRole findByUidAdRid(@Param("userid") Long userId, @Param("rid") Long rId);

    int subtractRoles(Long userId, Long rId);
}
