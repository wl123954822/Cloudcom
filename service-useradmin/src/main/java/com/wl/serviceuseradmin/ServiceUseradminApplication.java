package com.wl.serviceuseradmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.wl.serviceuseradmin.dao")
public class ServiceUseradminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUseradminApplication.class, args);
    }
}
