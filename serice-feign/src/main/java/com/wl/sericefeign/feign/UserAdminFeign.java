package com.wl.sericefeign.feign;

import com.wl.sericefeign.hystric.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-useradmin")
public interface UserAdminFeign {

    @RequestMapping(value = "/config/user",method = RequestMethod.GET)
    Object getUser();

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    Object login();
}
