package com.wl.adminfeign.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.adminfeign.feign.UserAdminFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class adminController {

    @Autowired
    private UserAdminFeign userAdminFeign;

    @PostMapping(value = "/login")
    public JSONObject login(@RequestParam("username") String username, @RequestParam("password") String password) {

        JSONObject userJson = userAdminFeign.login(username,password);
        System.out.println(userJson);
        JSONObject openIdJson = userJson.getJSONObject("result");
        String openId = openIdJson.getString("data");
        System.out.println(openId);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
        JSONObject adminJson = userAdminFeign.admin(openId,request,response);
        return adminJson;
    }
}
