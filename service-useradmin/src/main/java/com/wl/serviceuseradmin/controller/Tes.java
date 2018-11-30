package com.wl.serviceuseradmin.controller;



import com.alibaba.fastjson.JSONObject;
import com.wl.serviceuseradmin.entity.Menu;
import com.wl.serviceuseradmin.entity.User;
import com.wl.serviceuseradmin.enu.ResultEnum;
import com.wl.serviceuseradmin.service.MenuService;
import com.wl.serviceuseradmin.vo.Result;
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
    public JSONObject currentUser(String token) {
        JSONObject jsonObject = new JSONObject();
        User user = (User) redisTemplate.opsForValue().get(token);
        if (user != null) {
            List<Menu> menu = menuService.getMenusByUserId(user.getId());
            jsonObject.put("menu",menu);
            jsonObject.put("user",user);
        } else {
            user = new User();
        }
        return Result.result(ResultEnum.SUCCESS, jsonObject, "success");
    }
}
