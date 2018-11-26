package com.wl.serviceuseradmin.service;



import com.wl.serviceuseradmin.entity.Role;

import java.util.List;

public interface RoleService {

    int addRoles(String name, String nameZh);

    int deleteRoles(Long rid);

    List<Role> showRolesList();

    int giveRoles(Long userId, Long rId);

    int subtractRoles(Long userId, Long rId);
}
