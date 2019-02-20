package com.ps.tx.md.order.service;

import com.ps.tx.md.order.dao.model.MdOrder;
import com.ps.tx.md.order.dao.repository.OrderRepository;
import com.ps.tx.md.order.model.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @JmsListener(destination = "order:locked", containerFactory = "msgFactory")
    public void handleNewOrder(OrderDTO dto) {
        logger.info("Got new order to create {}", dto.toString());
        if (this.orderRepository.findOneByUuid(dto.getUuid()) != null) {
            logger.warn("order has been processed.");
        } else {
            MdOrder mdOrder = this.transform(dto);
            this.orderRepository.addOrder(mdOrder);
        }
        dto.setStatus("NEW");
        this.jmsTemplate.convertAndSend("order:pay", dto);
    }

    private MdOrder transform(OrderDTO dto) {
        MdOrder mdOrder = new MdOrder();
        mdOrder.setUuid(dto.getUuid());
        mdOrder.setAmount(dto.getAmount());
        mdOrder.setTitle(dto.getTitle());
        mdOrder.setCustomerId(dto.getCustomerId());
        mdOrder.setTicketNumber(dto.getTicketNumber());
        mdOrder.setStatus("NEW");
        return mdOrder;
    }
}
