package com.ps.tx.md.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
@EnableEurekaServer
public class RegistryCenterBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegistryCenterBootApplication.class, args);
    }
}
