package com.keysoft.datatranx.service;

import com.keysoft.datatranx.dao.ITicketDAO;
import com.keysoft.datatranx.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private ITicketDAO ticketDAO;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketDAO.findAll();
    }

    @Override
    public Optional<Ticket> getTicketById(int ticketId) {
        return ticketDAO.findById((long)ticketId);
    }

    @Override
    public void addTicket(Ticket ticket) {

        ticketDAO.save(ticket);
    }

    @Override
    public void updateTicket(Ticket updatedTicket) {
        Ticket currentTicket = getTicketById(updatedTicket.getId().intValue()).get();

        currentTicket.setStatus(updatedTicket.getStatus());
        currentTicket.setApplication_id(updatedTicket.getApplication_id());
        currentTicket.setDescription(updatedTicket.getDescription());
        currentTicket.setTitle(updatedTicket.getTitle());
    }

    @Override
    public void deleteTicket(int ticketId) {
        ticketDAO.delete(getTicketById(ticketId).get());
    }

}
