package com.ps.infrastructure.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActiveMQProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveMQProducerApplication.class,args);
    }
}
