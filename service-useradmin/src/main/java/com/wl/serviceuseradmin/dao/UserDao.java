package com.wl.serviceuseradmin.dao;


import com.wl.serviceuseradmin.entity.Role;
import com.wl.serviceuseradmin.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    User loadUserByUserName(String userName);

    List<Role> getRolesByUserId(Long id);

    int userReg(@Param("username") String username, @Param("password") String password);

    List<User>  getUsersByKeywords(@Param("keywords") String keywords);

    int updateUset(User user);

    int deleteRoleByUserId(Long userId);

    int addRolesForUser(@Param("userId") Long hrId, @Param("rids") Long[] rids);

    User getUserById(Long userId);

    int deleteUser(Long userId);

    List<User> getAllUser();

    User getUserByOpenId(@Param("openId") String openId);
}
