package com.ps.ms.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.ps.ms.user.dao.mapper")
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
public class MSUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSUserApplication.class,args);
    }
}
