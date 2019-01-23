package com.javastudy.service;

import com.javastudy.dao.CustomerRepository;
import com.javastudy.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerServiceTxInAnnotation {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public Customer create(Customer customer){
        if(customer.getId() != null){
            throw new RuntimeException("user not exists.");
        }
        customer.setUsername("Annotation:" + customer.getUsername());
        logger.info(customer.toString());
        this.customerRepository.save(customer);
        this.jmsTemplate.convertAndSend("customer:msg:reply",customer.getUsername());
        return customer;
    }
    @JmsListener(destination = "customer:msg:new")
    public void create(String name){
        logger.info("CustomerService in annotation by listener create customer:{}");
        Customer customer = new Customer();
        customer.setUsername("annotation:" + name);
        customer.setPassword("111111");
        customer.setRole("User");
        customerRepository.save(customer);
        this.jmsTemplate.convertAndSend("customer:msg:reply",customer.getUsername());

    }
}
