package com.ps.tx.md.ticket.dao;

import com.ps.tx.md.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByOwner(Long ownerId);
}
