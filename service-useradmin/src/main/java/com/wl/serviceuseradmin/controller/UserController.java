package com.wl.serviceuseradmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.serviceuseradmin.entity.Role;
import com.wl.serviceuseradmin.entity.User;
import com.wl.serviceuseradmin.enu.DataEnum;
import com.wl.serviceuseradmin.enu.ResultEnum;
import com.wl.serviceuseradmin.service.UserService;
import com.wl.serviceuseradmin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户管理接口
 * 1.用户注册 ---不对外开放注册接口，需要管理员分配
 * 2.用户登录 ---登录后台管理
 * 3.用户注销 ---清除当前session
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 用户登录接口
     */
    @RequestMapping("/login")
    public JSONObject login(String username, String password,String token){
       User userDetails =  userService.loadUserByUsername(username);
       redisTemplate.opsForValue().set(token,userDetails);
    return Result.result(ResultEnum.SUCCESS,userDetails,"success");
    }

    @RequestMapping("/register")
    public JSONObject register(String username, String password, String token) {
        // 获取当前登录用户
        User user = (User) redisTemplate.opsForValue().get(token);
        // 获取当前登录用户的权限
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (DataEnum.超级管理员.getDesc().equals(role.getName())) {
                int result = userService.UserReg(username,password);
                return Result.addResult(result);
            } else {
                return Result.result(ResultEnum.NO_JURISDICTION, "error");
            }
        }
        return null;
    }

   /* @RequestMapping("/logout")
    public JSONObject logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth !=null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return Result.result(ResultEnum.LOGIN_OUT_SUCCESS, "success");
    }*/

    @RequestMapping("/allUser")
    public JSONObject allUser () {
        List<User> users = userService.getAllUser();
        return Result.result(ResultEnum.SUCCESS,users,"success");
    }
}
