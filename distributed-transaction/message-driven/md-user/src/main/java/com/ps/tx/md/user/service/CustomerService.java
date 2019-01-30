package com.ps.tx.md.user.service;

import com.ps.tx.md.order.model.OrderDTO;
import com.ps.tx.md.user.dao.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @JmsListener(destination = "order:pay", containerFactory = "msgFactory")
    public void handleOrderPay(OrderDTO dto) {

    }
}
