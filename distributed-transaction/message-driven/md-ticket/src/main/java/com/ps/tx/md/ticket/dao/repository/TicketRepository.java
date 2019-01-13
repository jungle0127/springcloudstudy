package com.ps.tx.md.ticket.dao.repository;

import com.ps.tx.md.ticket.dao.mapper.MdTicketMapper;
import com.ps.tx.md.ticket.dao.model.MdTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {
    @Autowired
    private MdTicketMapper ticketMapper;
    public MdTicket save(MdTicket ticket){
        this.ticketMapper.updateByPrimaryKeySelective(ticket);
        return ticket;
    }
    public int lockTicket(Long customerId, Long ticketNumber){
        return this.ticketMapper.updateByTicketNumber4LockTicket(customerId,ticketNumber);
    }
}
