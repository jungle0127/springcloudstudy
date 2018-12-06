package com.javastudy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class CustomerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Transactional
    @JmsListener(destination = "customer:msg:new",containerFactory = "jmsListenerContainerFactory")
    public void handleMsg(String msg){
        logger.info("get message:{}",msg);
        String reply = "reply-" + msg;
        this.jmsTemplate.convertAndSend("customer:msg:reply",reply);
        if(msg.contains("error")){
            this.simulateException();
        }
    }
    @JmsListener(destination = "customer:msgcode:new",containerFactory = "jmsListenerContainerFactory")
    public void handleInCode(String msg){
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = this.transactionManager.getTransaction(transactionDefinition);
        try{
            String reply = "reply-code-" + msg;
            this.jmsTemplate.convertAndSend("customer:msg:reply",reply);
            if(msg.contains("error")){
                transactionManager.rollback(transactionStatus);
            }
            else {
                transactionManager.commit(transactionStatus);
            }
        }
        catch (Exception e){
            transactionManager.rollback(transactionStatus);
            throw e;
        }
    }
    private void simulateException(){
        throw new RuntimeException("pseudo exception.");
    }
}
