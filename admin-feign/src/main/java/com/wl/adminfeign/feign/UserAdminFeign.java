package com.wl.adminfeign.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FeignClient(value = "service-useradmin")
public interface UserAdminFeign {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    JSONObject login(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping(value = "/admin",method = RequestMethod.POST)
    JSONObject admin(@RequestParam("openid") String openId,@RequestParam("request") HttpServletRequest request,@RequestParam("response") HttpServletResponse response);
}
