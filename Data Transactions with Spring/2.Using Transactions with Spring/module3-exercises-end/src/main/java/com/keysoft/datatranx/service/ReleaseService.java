package com.keysoft.datatranx.service;

import com.keysoft.datatranx.dao.IReleaseDAO;
import com.keysoft.datatranx.dao.ITicketDAO;
import com.keysoft.datatranx.model.Release;
import com.keysoft.datatranx.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReleaseService implements IReleaseService{
    @Autowired
    private ITicketDAO ticketDAO;

    @Autowired
    private IReleaseDAO releaseDAO;

    @Override
    public void addRelease(Release release) {
        releaseDAO.addRelease(release);
    }

    @Override
    public Release getRelease(int releaseId) {
        return releaseDAO.getReleaseId(releaseId);
    }

    @Override
    public Release scheduleRelease(Release release) {
        /*Transaction consists of two steps:
            1.) Assign a ticket to an existing release as one database tranx via releaseDAO
            2.) Update the status on existing tickets to deployed status as another database tranx via ticketDAO
        */

        //STEP 1a - Create the new Tickets
        release.getTickets().forEach(ticket -> {
            ticketDAO.addTicket(ticket);
        });

        //STEP 1b - Associate the Tickets to a Release
        Release releaseToUpdate = getRelease(release.getId());
        releaseToUpdate.setTickets(release.getTickets());

        //STEP 2 - Update the Tickets to Completed only after associating them to a release
        release.getTickets().forEach(ticket -> {
            ticketDAO.getTicketById(ticket.getId()).setStatus("COMPLETED");
        });

        return release;
    }
}
