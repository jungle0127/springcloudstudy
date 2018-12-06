package com.javastudy.controller;

import com.javastudy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/message/listen")
    public void createMsg(@RequestParam String msg){
        this.jmsTemplate.convertAndSend("customer:msg:new",msg);
    }
    @PostMapping("/message/direct")
    public void handle(@RequestParam String msg){
        this.customerService.handleMsg(msg);
    }
    @PostMapping("/message/code/listen")
    public void createMsgCode(@RequestParam String msg){
        this.jmsTemplate.convertAndSend("customer:msgcode:new",msg);
    }
    @PostMapping("/message/code/direct")
    public void handleCode(@RequestParam String msg){
        this.customerService.handleInCode(msg);
    }
    @GetMapping("/message")
    public String getMessage(){
        Object obj = this.jmsTemplate.receiveAndConvert("customer:msg:reply");
        return String.valueOf(obj);
    }
}
