package com.ps.tx.md.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.ps.tx.md.order.dao.mapper")
public class OrderBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderBootApplication.class,args);
    }
}
