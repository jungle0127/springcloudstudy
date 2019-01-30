package com.ps.infrastructure.mq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiverService {
    @JmsListener(destination = "ps.test.topic", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String text) {
        System.out.println(String.format("Received message <<<<<<============ %s", text));
    }
}
