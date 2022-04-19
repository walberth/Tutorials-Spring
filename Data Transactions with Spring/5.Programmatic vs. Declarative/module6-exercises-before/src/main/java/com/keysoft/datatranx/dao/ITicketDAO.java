package com.keysoft.datatranx.dao;


import com.keysoft.datatranx.model.Ticket;

import java.util.List;

public interface ITicketDAO {
    List<Ticket> getAllTickets();
    void addTicket(Ticket ticket);
    Ticket getTicketById(int ticketId);
    void updateTicket(Ticket ticket);
    void deleteTicket(int ticketId);
    void closeTicket(int ticketId);
}

