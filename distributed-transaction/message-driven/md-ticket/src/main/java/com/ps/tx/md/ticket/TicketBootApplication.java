package com.ps.tx.md.ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.ps.tx.md.ticket.dao.mapper")
@EnableSwagger2
@EnableEurekaClient
public class TicketBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketBootApplication.class, args);
    }
}
