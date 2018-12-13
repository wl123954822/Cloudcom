package com.wl.serviceuseradmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.serviceuseradmin.entity.Role;
import com.wl.serviceuseradmin.entity.User;
import com.wl.serviceuseradmin.enu.DataEnum;
import com.wl.serviceuseradmin.enu.ResultEnum;
import com.wl.serviceuseradmin.service.UserService;
import com.wl.serviceuseradmin.util.CookieUtil;
import com.wl.serviceuseradmin.util.PasswordUtil;
import com.wl.serviceuseradmin.vo.CookieConstant;
import com.wl.serviceuseradmin.vo.RedisConstant;
import com.wl.serviceuseradmin.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @RequestMapping("/adminlogin")
    public JSONObject login(@RequestParam("username") String username, @RequestParam("password") String password,HttpServletRequest request,HttpServletResponse response) {
        User user = userService.loadUserByUsername(username);
        String passwordStr = user.getPassword();
        // 获取数据库解密后的密码
        String dePassword = new String(PasswordUtil.AESDecrypt(PasswordUtil.parseHexStr2Byte(passwordStr), username));
        if (!dePassword.equals(password)) {
            return Result.result(ResultEnum.PASSWORD_ERROR, "error");
        } else {
            return this.adminLogin(user.getOpenId(),request,response);
        }
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

    @RequestMapping("/allUser")
    public JSONObject allUser () {
        List<User> users = userService.getAllUser();
        return Result.result(ResultEnum.SUCCESS,users,"success");
    }

    /**
     * mj登录
     * @return
     */
    @RequestMapping("/buyer")
    public JSONObject buyer(@RequestParam("openid") String openId,HttpServletRequest request,HttpServletResponse response) {
        // 判断是否已经登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.OPENID);
        if (cookie != null && !StringUtils.isEmpty(redisTemplate.opsForValue().get(String.format(RedisConstant.OPENID_TEMPLATE, cookie.getValue())))){
            return Result.result(ResultEnum.SUCCESS,"success");
        }
        // 1.查询用户
        User user = this.userService.loadUserByOpenid(openId);
        if (user == null) {
            return Result.result(ResultEnum.LOGIN_FAIL,"error");
        }
        // 判断角色
        List<Role> rolesList = user.getRoles();
        for (Role role : rolesList) {
            if (!role.getName().equals(DataEnum.BUYER)) {
                return Result.result(ResultEnum.ROLE_ERROR, "error");
            }
        }
        // 存到redis key=openid,value=openid
        Integer expire = CookieConstant.expire;
        redisTemplate.opsForValue().set(String.format(RedisConstant.OPENID_TEMPLATE,openId),openId,expire, TimeUnit.SECONDS);

        //cookie中设置openid
        CookieUtil.set(response,CookieConstant.OPENID,openId,expire);

        return Result.result(ResultEnum.SUCCESS,user,"success");
       /*
        rolesList.forEach(role -> {
            if (!role.getName().equals(DataEnum.BUYER)) {
                return Result.result(ResultEnum.ROLE_ERROR,"error");
            }
        });*/
    }


    public JSONObject adminLogin( String openId, HttpServletRequest request, HttpServletResponse response) {
        // 判断是否已经登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.OPENID);
        if (cookie != null && !StringUtils.isEmpty(redisTemplate.opsForValue().get(String.format(RedisConstant.OPENID_TEMPLATE, cookie.getValue())))) {
            return Result.result(ResultEnum.SUCCESS, "success");
        }
        // 1.查询用户
        User user = this.userService.loadUserByOpenid(openId);
        if (user == null) {
            return Result.result(ResultEnum.LOGIN_FAIL, "error");
        }
        // 判断角色
        List<Role> rolesList = user.getRoles();
        for (Role role : rolesList) {
            if (!role.getName().equals(DataEnum.超级管理员.getDesc())) {
                return Result.result(ResultEnum.ROLE_ERROR, "error");
            }
        }
        // 存到redis key=openid,value=openid
        Integer expire = CookieConstant.expire;
        redisTemplate.opsForValue().set(String.format(RedisConstant.OPENID_TEMPLATE, openId), openId, expire, TimeUnit.SECONDS);

        //cookie中设置openid
        CookieUtil.set(response, CookieConstant.OPENID, openId, expire);

        return Result.result(ResultEnum.SUCCESS, user, "success");
    }
}
