package com.wl.serviceuseradmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.serviceuseradmin.common.UserUtiles;
import com.wl.serviceuseradmin.entity.Role;
import com.wl.serviceuseradmin.entity.User;
import com.wl.serviceuseradmin.enu.DataEnum;
import com.wl.serviceuseradmin.enu.ResultEnum;
import com.wl.serviceuseradmin.service.RoleService;
import com.wl.serviceuseradmin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色
 * 1.当前登录用户角色权限
 * 2.创建角色权限，需要管理员添加
 * 3.删除角色权限，需要管理员删除
 * 4.显示角色权限列表，为添加角色
 * 5.添加角色权限，管理员为用户赋予角色权限
 * 6.删除角色权限，管理员为用户解除角色权限
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    /**
     * 当前登录用户的角色
     * @return
     */
    @RequestMapping("/roleByCurrentUser")
    public List<Role> allRolesLis() {
        User user = UserUtiles.getCurrentUser();
        List<Role> list = user.getRoles();
        return list;
    }

    /**
     *创建用户角色
     */
    @RequestMapping("/addRoles")
    public JSONObject addRoles(HttpServletResponse resp, String name , String nameZh) throws IOException {
        User user = UserUtiles.getCurrentUser();
        List<Role> userRole = user.getRoles();
        for (Role role : userRole) {
            if (DataEnum.超级管理员.getDesc().equals(role.getName())) {
                int resule = roleService.addRoles(name,nameZh);
                return Result.addResult(resule);
            } else {
                return Result.result(ResultEnum.NO_JURISDICTION, "error");
            }
        }
        return Result.nullResult();
    }
    /**
     * 删除用户角色权限,---将用户关联的权限全部删除
     */
    @RequestMapping("/deleteRoles")
    public JSONObject deleteRoles(Long rid) {
        User user = UserUtiles.getCurrentUser();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (DataEnum.超级管理员.getDesc().equals(role.getName())) {
                int result = roleService.deleteRoles(rid);
                return Result.result(ResultEnum.SUCCESS,"success");
            } else {
                return Result.result(ResultEnum.NO_JURISDICTION, "error");
            }
        }
        return Result.nullResult();
    }

    /**
     * 显示角色权限列表，为添加角色
     */
    @RequestMapping("/rolesList")
    public JSONObject showRolesList() {
        User user = UserUtiles.getCurrentUser();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (DataEnum.超级管理员.getDesc().equals(role.getName())) {
                List<Role> result = roleService.showRolesList();
                return Result.result(ResultEnum.SUCCESS, result,"success");
            } else {
                return Result.result(ResultEnum.NO_JURISDICTION, "error");
            }
        }
        return Result.nullResult();
    }

    /**
     * 添加角色权限，管理员为用户赋予角色
     */
    @RequestMapping("/giveRoles")
    public JSONObject giveRoles(Long userId,Long rId) {
        User user = UserUtiles.getCurrentUser();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (DataEnum.超级管理员.getDesc().equals(role.getName())) {
                int result = roleService.giveRoles(userId,rId);
                return Result.addResult(result);
            } else {
                return Result.result(ResultEnum.NO_JURISDICTION, "error");
            }
        }
        return Result.nullResult();
    }

    /**
     * 删除角色权限，管理员为用户解除角色权限
     */
    @RequestMapping("subtractRoles")
    public JSONObject subtractRoles(Long userId, Long rId) {
        User user = UserUtiles.getCurrentUser();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (DataEnum.超级管理员.getDesc().equals(role.getName())) {
                int result = roleService.subtractRoles(userId, rId);
                return Result.addResult(result);
            } else {
                return Result.result(ResultEnum.NO_JURISDICTION, "error");
            }
        }
        return Result.nullResult();
    }

    @RequestMapping("nzlhhh")
    public JSONObject nzlhhh(HttpServletRequest request , HttpServletResponse response) {
        request.getAttribute("userName");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",request);
        List<User> userList = new ArrayList<>();
        for (User user : userList) {
            List<Role> roles =  user.getRoles();
            Object[] arrays = roles.toArray();
        }
        jsonObject.put("userName", request);
        return jsonObject;


    }
}
