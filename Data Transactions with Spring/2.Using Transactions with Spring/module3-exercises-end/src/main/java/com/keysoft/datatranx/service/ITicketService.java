package com.keysoft.datatranx.service;


import com.keysoft.datatranx.model.Ticket;

import java.util.List;

public interface ITicketService {
    List<Ticket> getAllTickets();
    Ticket getTicketById(int ticketId);
    void addTicket(Ticket ticket);
    void updateTicket(Ticket ticket);
    void deleteTicket(int ticketId);
    void closeTicket(int ticketId);
}
