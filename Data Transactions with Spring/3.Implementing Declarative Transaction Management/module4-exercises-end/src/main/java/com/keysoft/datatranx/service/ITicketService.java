package com.keysoft.datatranx.service;


import com.keysoft.datatranx.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface ITicketService {
    List<Ticket> getAllTickets();
    Optional<Ticket> getTicketById(int ticketId);
    void addTicket(Ticket ticket);
    void updateTicket(Ticket ticket);
    void deleteTicket(int ticketId);
}
