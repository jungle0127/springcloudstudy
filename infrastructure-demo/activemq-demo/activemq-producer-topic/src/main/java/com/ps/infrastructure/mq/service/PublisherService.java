package com.ps.infrastructure.mq.service;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class PublisherService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    public void sendMsg(String destinationName, String message){
        Destination destination = new ActiveMQTopic(destinationName);

        this.jmsMessagingTemplate.convertAndSend(destinationName,message);
    }
}
