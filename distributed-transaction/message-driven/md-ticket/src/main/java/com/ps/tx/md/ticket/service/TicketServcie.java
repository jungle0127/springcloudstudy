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
    public MdTicket ticketLock(OrderDTO orderDTO){
        MdTicket ticket = this.ticketRepository.findByTicketNumber(orderDTO.getTicketNumber());
        ticket.setLockUser(orderDTO.getCustomerId());
        ticket = ticketRepository.save(ticket);
        return ticket;
    }
}
