package com.ps.tx.md.ticket.dao.repository;

import com.ps.tx.md.ticket.dao.mapper.TicketMapper;
import com.ps.tx.md.ticket.dao.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {
    @Autowired
    private TicketMapper ticketMapper;
    public Ticket save(Ticket ticket){
        this.ticketMapper.insert(ticket);
        return ticket;
    }
}
