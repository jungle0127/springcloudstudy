package com.ps.infrastructure.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TopicConsumerApplication {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.setDaemon(true);
        thread.start();
        SpringApplication.run(TopicConsumerApplication.class, args);
    }
}
