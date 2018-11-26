package com.wl.serviceuseradmin.controller;

import com.alibaba.fastjson.JSONObject;

import com.wl.serviceuseradmin.common.UserUtiles;
import com.wl.serviceuseradmin.entity.Menu;
import com.wl.serviceuseradmin.entity.Role;
import com.wl.serviceuseradmin.entity.User;
import com.wl.serviceuseradmin.enu.DataEnum;
import com.wl.serviceuseradmin.enu.ResultEnum;
import com.wl.serviceuseradmin.service.MenuService;
import com.wl.serviceuseradmin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 1.menu -添加menu
 * 2.删除menu
 * 3.分配menu
 * 4.解除menu
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/menuByUsers")
    public List<Menu> menuListByUsers(){
        return menuService.getMenusByUserId();
    }

    /**
     * enu -添加menu
     * @param menu
     * @return
     */
    @RequestMapping("/addMenus")
    public JSONObject addMenus(Menu menu) {
        User user = UserUtiles.getCurrentUser();
        List<Role> roles = user.getRoles();
            for (Role role : roles) {
                if (DataEnum.超级管理员.getDesc().equals(role.getName())) {
                    int resule = menuService.addMenu(menu);
                    return Result.addResult(resule);
                } else {
                    return Result.result(ResultEnum.NO_JURISDICTION, "error");
                }
            }
            return Result.nullResult();
    }

    /**
     * 删除menu
     * @param mid
     * @return
     */
    @RequestMapping("/deleteMenus")
    public JSONObject deleteMenus(Long mid) {
        User user = UserUtiles.getCurrentUser();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (DataEnum.超级管理员.getDesc().equals(role.getName())) {
                int result = menuService.deleteMenu(mid);
                return Result.result(ResultEnum.SUCCESS, "success");
            } else {
                return Result.result(ResultEnum.NO_JURISDICTION, "error");
            }
        }
        return Result.nullResult();
    }

    /**
     * 分配menu
     * @param mId
     * @param rId
     * @return
     */
    @RequestMapping("/giveMenus")
    public JSONObject giveRoles(Long mId, Long rId) {
        User user = UserUtiles.getCurrentUser();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (DataEnum.超级管理员.getDesc().equals(role.getName())) {
                int result = menuService.giveMenus(mId, rId);
                return Result.addResult(result);
            } else {
                return Result.result(ResultEnum.NO_JURISDICTION, "error");
            }
        }
        return Result.nullResult();
    }

    /**
     * 减除menu
     * @param mId
     * @param rId
     * @return
     */
    @RequestMapping("subtractMenus")
    public JSONObject subtractRoles(Long mId, Long rId) {
        User user = UserUtiles.getCurrentUser();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (DataEnum.超级管理员.getDesc().equals(role.getName())) {
                int result = menuService.subtractMenus(mId, rId);
                return Result.addResult(result);
            } else {
                return Result.result(ResultEnum.NO_JURISDICTION, "error");
            }
        }
        return Result.nullResult();
    }
}
