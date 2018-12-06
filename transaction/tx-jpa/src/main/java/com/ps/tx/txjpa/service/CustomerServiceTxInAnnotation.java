package com.ps.tx.txjpa.service;

import com.ps.tx.txjpa.dao.CustomerRepository;
import com.ps.tx.txjpa.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerServiceTxInAnnotation {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CustomerRepository customerRepository;
    @Transactional
    public Customer create(Customer customer){
        if(customer.getId() != null){
            throw new RuntimeException("user not exists.");
        }
        customer.setUsername("Annotation:" + customer.getUsername());
        logger.info(customer.toString());
        return this.customerRepository.save(customer);
    }
}
