package com.javastudy.service;

import com.javastudy.dao.CustomerRepository;
import com.javastudy.domain.Customer;
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
public class CustomerServiceTxInCode {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    @Autowired
    private JmsTemplate jmsTemplate;

    public Customer create(Customer customer) {
        if (customer.getId() != null) {
            throw new RuntimeException("user exists.");
        }
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        defaultTransactionDefinition.setTimeout(15);
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(defaultTransactionDefinition);
        try {
            customer.setUsername("code" + customer.getUsername());
            customerRepository.save(customer);
            this.jmsTemplate.convertAndSend("customer:msg:reply", customer.getUsername());
            platformTransactionManager.commit(transactionStatus);
            return customer;
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
            logger.error(e.getLocalizedMessage());
            throw e;
        }
    }

}
