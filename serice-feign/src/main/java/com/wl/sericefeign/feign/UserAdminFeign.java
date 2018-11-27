package com.wl.sericefeign.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-useradmin")
public interface UserAdminFeign {

    @RequestMapping(value = "/config/user",method = RequestMethod.POST)
    JSONObject getUser(@RequestParam("token") String token);

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    JSONObject login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("token") String token);


}
