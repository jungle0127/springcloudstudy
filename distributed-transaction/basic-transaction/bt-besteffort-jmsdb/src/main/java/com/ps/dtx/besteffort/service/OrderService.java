package com.ps.dtx.besteffort.service;

import com.ps.dtx.besteffort.dao.OrderRepository;
import com.ps.dtx.besteffort.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private OrderRepository orderRepository;
    @Transactional
    @JmsListener(destination = "msg:new:order")
    public void handleNewOrder(String jmsMessage){
        this.logger.info(jmsMessage);
        Order order = new Order();
        order.setId(11L);
        order.setAmount(10);
        order.setCustomerId(229200L);
        order.setStorageId(1L);
        order.setOrderDesc("JMS best effor order");
        order.setCreateTime(new Date());
        this.orderRepository.save(order);
        this.jmsTemplate.convertAndSend("msg:new:order:reply","new order created from reply msg.");
    }
    @JmsListener(destination = "msg:new:order:reply")
    public void handleNewOrderReply(String jmsMessage){
        System.out.println(jmsMessage);
    }
}
