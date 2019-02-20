package com.ps.dtx.md.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@MapperScan("com.ps.dtx.md.account.dao.mapper")
@EnableSwagger2
public class MDAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(MDAccountApplication.class,args);
    }
}
