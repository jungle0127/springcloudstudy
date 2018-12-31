package com.ps.tx.md.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TicketBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketBootApplication.class,args);
    }
}
