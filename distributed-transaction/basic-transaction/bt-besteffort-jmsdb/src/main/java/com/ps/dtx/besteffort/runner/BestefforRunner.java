package com.ps.dtx.besteffort.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class BestefforRunner implements CommandLineRunner {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Override
    public void run(String... args) throws Exception {
        this.jmsTemplate.convertAndSend("msg:new:order","send message to trigger new order.");
    }
}
