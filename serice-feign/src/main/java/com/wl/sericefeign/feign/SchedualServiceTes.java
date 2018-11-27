package com.wl.sericefeign.feign;

import com.wl.sericefeign.hystric.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-tes")
public interface SchedualServiceTes {

    @RequestMapping(value = "/tes", method = RequestMethod.GET)
    String tesClientOne();
}
