package com.wl.serviceuseradmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
/*@EnableEurekaClient
@EnableDiscoveryClient*/
@MapperScan("com.wl.serviceuseradmin.dao")
public class ServiceUseradminApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ServiceUseradminApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ServiceUseradminApplication.class, args);
    }
}
