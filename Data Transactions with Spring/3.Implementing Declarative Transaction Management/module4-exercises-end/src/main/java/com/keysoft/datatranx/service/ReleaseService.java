package com.keysoft.datatranx.service;

import com.keysoft.datatranx.dao.IReleaseDAO;
import com.keysoft.datatranx.dao.ITicketDAO;
import com.keysoft.datatranx.model.Release;
import com.keysoft.datatranx.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class ReleaseService implements IReleaseService{
    @Autowired
    private ITicketDAO ticketDAO;

    @Autowired
    private IReleaseDAO releaseDAO;

    @Override
    public void addRelease(Release release) {
        releaseDAO.save(release);
    }

    @Override
    public Optional<Release> getRelease(int releaseId) {
        return releaseDAO.findById((long)releaseId);
    }

    @Override
    public Release scheduleRelease(Release release) {
        /*Transaction consists of two steps:
            1.) Assign a ticket to an existing release as one database tranx via releaseDAO
            2.) Update the status on existing tickets to deployed status as another database tranx via ticketDAO
        */
        createTickets(release);
        assignTicketsToRelease(release.getId(), release.getTickets());

        return release;
    }

    private void createTickets(Release release) {
        //STEP 1a - Create the new Tickets
        release.getTickets().forEach(ticket -> {
            ticketDAO.save(ticket);
        });
    }

    private void assignTicketsToRelease(Long id, List<Ticket> tickets) {
        //STEP 1b - Associate the Tickets to a Release
        Release releaseToUpdate = getRelease(id.intValue()).get();
        releaseToUpdate.setTickets(tickets);

        //STEP 2 - Update the Tickets to Completed only after associating them to a release
        releaseToUpdate.getTickets().forEach(ticket -> {
           ticket.setStatus("COMPLETED");
        });
    }
}
