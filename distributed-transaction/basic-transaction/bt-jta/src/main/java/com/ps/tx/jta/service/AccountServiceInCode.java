package com.ps.tx.jta.service;

import com.ps.tx.jta.dao.domain.AccountRepository;
import com.ps.tx.jta.dao.model.TAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;

@Service
public class AccountServiceInCode {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    @Autowired
    private JmsTemplate jmsTemplate;

    public TAccount createCustomer(TAccount account) throws Exception {
        if (account.getId() != null) {
            throw new RuntimeException("Account exists.");
        }
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        defaultTransactionDefinition.setTimeout(15);
        TransactionStatus transactionStatus = this.platformTransactionManager.getTransaction(defaultTransactionDefinition);
        try {
            account.setCustomerId(account.getCustomerId());
            account.setDeposit(account.getDeposit());
            account.setCreateTime(new Date());
            account.setUpdateTime(new Date());
            accountRepository.addAccount(account);
            this.jmsTemplate.convertAndSend("account:msg:reply", account.toString());
            platformTransactionManager.commit(transactionStatus);
            return account;
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
            logger.error("Got error:{}", e.getMessage());
            throw e;
        }

    }
}
