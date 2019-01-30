package com.ps.infrastructure.mq.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;


@Service
public class ProducerServcie {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(String destinationName, String message) {
        System.out.println("========>>>> Send queue message " + message);
        Destination destination = new ActiveMQQueue(destinationName);
        this.jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
