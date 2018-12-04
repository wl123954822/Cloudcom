package com.wl.serviceuseradmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.serviceuseradmin.entity.Role;
import com.wl.serviceuseradmin.entity.User;
import com.wl.serviceuseradmin.enu.DataEnum;
import com.wl.serviceuseradmin.enu.ResultEnum;
import com.wl.serviceuseradmin.service.UserService;
import com.wl.serviceuseradmin.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @author wanglei
 */
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 用户登录接口
     */
    @RequestMapping("/login")
    public JSONObject login(String username, String password,String token){
        User userDetails = null;
        // 不需要每次都重新保存redis，若发生异常错误，进行处理
        try {
            userDetails = (User) redisTemplate.opsForValue().get(token);
            logger.info("获取redis用户信息");
            if (userDetails == null) {
                logger.info("未取到用户信息");
                userDetails = userService.loadUserByUsername(username);
                redisTemplate.opsForValue().set(token, userDetails);
            }
        } catch (Exception e) {
            logger.error("redis获取失败"+e);
            // 将之前token删除，重新赋值
            redisTemplate.delete(token);
            userDetails = userService.loadUserByUsername(username);
            redisTemplate.opsForValue().set(token, userDetails);
        }
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
