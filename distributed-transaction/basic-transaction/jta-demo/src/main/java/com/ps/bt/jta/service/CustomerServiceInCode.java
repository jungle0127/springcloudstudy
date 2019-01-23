package com.ps.bt.jta.service;

import com.ps.bt.jta.dao.CustomerRepository;
import com.ps.bt.jta.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class CustomerServiceInCode {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    @Autowired
    private JmsTemplate jmsTemplate;

    public Customer createCustomer(Customer customer) throws Exception {
        if(customer.getId() != null){
            throw new RuntimeException("User exists.");
        }
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        defaultTransactionDefinition.setTimeout(15);
        TransactionStatus transactionStatus = this.platformTransactionManager.getTransaction(defaultTransactionDefinition);
        try{
            customer.setUsername("Code: " + customer.getUsername());
            customerRepository.save(customer);
            this.jmsTemplate.convertAndSend("customer:msg:reply",customer.getUsername());
            platformTransactionManager.commit(transactionStatus);
            return customer;
        } catch (Exception e){
            platformTransactionManager.rollback(transactionStatus);
            logger.error("Got error:{}", e.getMessage());
            throw e;
        }

    }
}
