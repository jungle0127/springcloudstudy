package com.ps.tx.md.ticket.service;

import com.ps.tx.md.order.model.OrderDTO;
import com.ps.tx.md.ticket.dao.model.MdTicket;
import com.ps.tx.md.ticket.dao.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@Service
public class TicketServcie {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = "order:new",containerFactory = "jmsListenerContainerFactory")
    public void handleTicketLock(OrderDTO dto){
        logger.info("Got new order for tikcket lock.");
        int lockCount = this.ticketRepository.lockTicket(dto.getCustomerId(),dto.getTicketNumber());
        if(lockCount == 1){
            logger.info("Lock ticket succeed.");
            dto.setStatus("TICKET_LOCKED");
            this.jmsTemplate.convertAndSend("order:locked",dto);// 触发创建订单的操作
        }
        else{
            logger.warn("Lock ticket failed.");
            dto.setStatus("TICKET_LOCK_FAILED.");
            //TODO: need more process
        }


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
