package com.keysoft.datatranx.service;

import com.keysoft.datatranx.dao.IReleaseDAO;
import com.keysoft.datatranx.dao.ITicketDAO;
import com.keysoft.datatranx.model.Release;
import com.keysoft.datatranx.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReleaseService implements IReleaseService{
    @Autowired
    private ITicketDAO ticketDAO;

    @Autowired
    private IReleaseDAO releaseDAO;

    @Autowired
    private PlatformTransactionManager transactionManager;

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

        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        try {
            createTickets(release);
            assignTicketsToRelease(release.getId(), release.getTickets());
            transactionManager.commit(transactionStatus);
        } catch (RuntimeException e) {
            transactionManager.rollback(transactionStatus);
            throw e;
        }

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
