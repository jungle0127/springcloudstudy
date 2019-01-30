package com.ps.tx.md.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
@MapperScan("com.ps.tx.md.user.dao.mapper")
public class UserBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserBootApplication.class, args);
    }
}
