package com.ps.infrastructure.mq.runner;

import com.ps.infrastructure.mq.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SendMessageRunner implements CommandLineRunner {
    @Autowired
    private PublisherService publisherService;

    public void run(String... args) throws Exception {
        for(int index = 0;index< 10;index++){
            this.publisherService.sendMsg("ps.test.topic",String.format("Send topic message with NO.%s",index));
        }
    }
}
