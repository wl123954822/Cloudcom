package com.wl.adminfeign.feign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wl.entityvo.Menu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@FeignClient(value = "service-useradmin")
public interface UserAdminFeign {

    @RequestMapping(value = "/menu/menuByUsers",method = RequestMethod.POST)
    JSONObject menuList(@RequestParam("openid") String openid);

}
