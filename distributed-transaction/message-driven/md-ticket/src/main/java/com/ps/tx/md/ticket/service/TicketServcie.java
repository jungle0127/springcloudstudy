package com.ps.tx.md.ticket.service;

import com.ps.tx.md.order.model.OrderDTO;
import com.ps.tx.md.ticket.dao.model.MdTicket;
import com.ps.tx.md.ticket.dao.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@Service
public class TicketServcie {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TicketRepository ticketRepository;
    @JmsListener(destination = "order:new",containerFactory = "jmsListenerContainerFactory")
    public void handleTicketLock(MdTicket ticket){
        logger.info("Got new order for tikcket lock.");

    }
    @Transactional
    public Integer ticketLock(OrderDTO orderDTO){
        int affectedRows = 0;
        try{
            affectedRows = this.ticketRepository.lockTicket(orderDTO.getCustomerId(),orderDTO.getTicketNumber());
        } catch (Exception e){
            logger.error(e.getLocalizedMessage());
        }
        logger.info("Try to lock ticket with result:{}",affectedRows);
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        return affectedRows;
    }
}
