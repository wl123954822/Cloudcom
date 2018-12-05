package com.wl.sericefeign.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-spcart")
public interface SpCartFeign {

    @RequestMapping(value = "/cart/add", method = RequestMethod.POST)
    JSONObject addCart(@RequestParam("token") String token);
}
