package com.wl.serviceuseradmin.controller;



import com.wl.serviceuseradmin.entity.Menu;
import com.wl.serviceuseradmin.entity.User;
import com.wl.serviceuseradmin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class Tes {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/user")
    public User currentUser(String token) {
        User user = (User) redisTemplate.opsForValue().get(token);
        if (user != null) {
            List<Menu> menu = menuService.getMenusByUserId(user.getId());
            user.setMenus(menu);
        } else {
            user = new User();
        }
        return user;
    }
}
