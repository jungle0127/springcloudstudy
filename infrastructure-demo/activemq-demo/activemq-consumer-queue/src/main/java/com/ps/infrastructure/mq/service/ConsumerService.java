package com.ps.infrastructure.mq.service;

import com.ps.infrastructure.mq.msg.model.PseudoMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @JmsListener(destination = "ps.test.queue")
    public void receiveMsg(String text) {
        System.out.println("Receive message <<<<<<====== " + text);
    }
    @JmsListener(destination = "ps.test.data.queue")
    public void receiveMsg(PseudoMessage pseudoMessage){
        System.out.println(" Receive data class <<<<====" + pseudoMessage.toString());
    }
}
