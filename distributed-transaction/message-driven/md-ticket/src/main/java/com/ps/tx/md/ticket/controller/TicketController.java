package com.ps.tx.md.ticket.controller;

import com.ps.tx.md.ticket.dao.TicketRepository;
import com.ps.tx.md.ticket.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;
    @PostConstruct
    public void init(){
        Ticket ticket = new Ticket();
        ticket.setName("NO.1");
        ticket.setTicketNumber(100L);
        ticketRepository.save(ticket);
    }

}
