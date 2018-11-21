package com.wl.sericefeign.hystric;

import com.wl.sericefeign.feign.SchedualServiceTes;
import org.springframework.stereotype.Component;

/**
 * 断路器
 */
@Component
public class SchedualServiceHiHystric  implements SchedualServiceTes {
    @Override
    public String tesClientOne(String name) {
        return name;
    }
}
