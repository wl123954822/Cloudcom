package com.wl.serviceuseradmin.service.impl;


import com.wl.serviceuseradmin.dao.MenuRoleDao;
import com.wl.serviceuseradmin.dao.RolesDao;
import com.wl.serviceuseradmin.dao.UserRoleDao;
import com.wl.serviceuseradmin.entity.Role;
import com.wl.serviceuseradmin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RolesDao rolesDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private MenuRoleDao menuRoleDao;

    @Override
    public int addRoles(String name, String nameZh) {

        // 对参数非空校验
        if ("".equals(name) || name == null) {
            return -2;
        }
        if ("".equals(nameZh) || nameZh == null) {
            return -2;
        }
        //如果用户名存在，返回错误
        if (rolesDao.loadRolesByName(name) != null) {
            return -1;
        }

        return rolesDao.addRolesByAdmin(name,nameZh);
    }

    @Override
    public int deleteRoles(Long rid) {
        // 对参数非空校验
        if ("".equals(rid) || rid == null) {
            return -2;
        }
        return rolesDao.deleteRoles(rid);
    }

    @Override
    public List<Role> showRolesList() {
        return rolesDao.showRolesList((long) 1);
    }

    @Override
    public int giveRoles(Long userId, Long rId) {
        // 对参数非空校验
        if ("".equals(userId) || userId == null) {
            return -2;
        }
        // 对参数非空校验
        if ("".equals(rId) || rId == null) {
            return -2;
        }
        //如果用户名存在，返回错误
        if (userRoleDao.findByUidAdRid(userId,rId) != null) {
            return -1;
        }

        return userRoleDao.giveRoles(userId,rId);
    }

    @Override
    public int subtractRoles(Long userId, Long rId) {
        // 对参数非空校验
        if ("".equals(userId) || userId == null) {
            return -2;
        }
        // 对参数非空校验
        if ("".equals(rId) || rId == null) {
            return -2;
        }
        return userRoleDao.subtractRoles(userId, rId);
    }
}
