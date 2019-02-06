package com.ps.tx.jta.service;

import com.ps.tx.jta.dao.domain.AccountRepository;
import com.ps.tx.jta.dao.model.TAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceInAnnotation {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Transactional
    public TAccount createAccount(TAccount account){
        if(account.getId() != null){
            logger.info("Account exists.");
            return account;
        }
        this.accountRepository.addAccount(account);
        this.jmsTemplate.convertAndSend("account:msg:reply",account.toString());
        return account;
    }
    @JmsListener(destination = "account:msg:reply")
    public void receiveAddAccountMessage(String message){
        System.out.println(message);
    }
}
