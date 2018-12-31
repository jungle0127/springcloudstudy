package com.ps.infrastructure.mq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @JmsListener(destination = "ps.test.queue")
    public void receiveMsg(String text){
        System.out.println("Receive message <<<<<<====== " + text);
    }
}
