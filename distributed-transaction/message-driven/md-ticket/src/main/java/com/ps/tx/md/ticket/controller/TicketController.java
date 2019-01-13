package com.ps.tx.md.ticket.controller;

import com.ps.tx.md.order.model.OrderDTO;
import com.ps.tx.md.ticket.dao.model.MdTicket;
import com.ps.tx.md.ticket.dao.repository.TicketRepository;
import com.ps.tx.md.ticket.service.TicketServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketServcie ticketServcie;
    @PostConstruct
    public void init(){
        MdTicket ticket = new MdTicket();
        ticket.setName("NO.1");
        ticket.setTicketNumber(100L);
        ticketRepository.save(ticket);
    }
    @PostMapping("/lock")
    public Integer lockTicket(@RequestBody  OrderDTO orderDTO){
        return this.ticketServcie.ticketLock(orderDTO);
    }
}
