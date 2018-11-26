package com.wl.serviceuseradmin.controller;


import com.wl.serviceuseradmin.common.UserUtiles;
import com.wl.serviceuseradmin.entity.Menu;
import com.wl.serviceuseradmin.entity.User;
import com.wl.serviceuseradmin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class Tes {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/sysmenu")
    public List<Menu> sysmenu() {
        return menuService.getMenusByUserId();
    }

    @RequestMapping("/user")
    public User currentUser() {
        return UserUtiles.getCurrentUser();
    }
}
