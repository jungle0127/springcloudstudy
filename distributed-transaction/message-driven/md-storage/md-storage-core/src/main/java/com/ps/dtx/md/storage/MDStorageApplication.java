package com.ps.dtx.md.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@EnableHystrix
@MapperScan("com.ps.dtx.md.storage.dao.mapper")
public class MDStorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MDStorageApplication.class,args);
    }
}
