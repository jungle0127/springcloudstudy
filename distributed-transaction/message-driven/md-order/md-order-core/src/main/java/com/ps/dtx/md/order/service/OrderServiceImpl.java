package com.ps.dtx.md.order.service;

import com.ps.dtx.md.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Override
    @JmsListener(destination = "md:order:queue:create",containerFactory = "jmsListenerContainerFactory")
    @Transactional
    public Order create(Order order) {

        return null;
    }
}
