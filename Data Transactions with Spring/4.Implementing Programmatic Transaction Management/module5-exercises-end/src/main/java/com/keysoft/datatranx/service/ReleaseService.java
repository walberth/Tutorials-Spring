package com.keysoft.datatranx.service;

import com.keysoft.datatranx.dao.IReleaseDAO;
import com.keysoft.datatranx.dao.ITicketDAO;
import com.keysoft.datatranx.model.Release;
import com.keysoft.datatranx.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

    private final TransactionTemplate transactionTemplate;

    public ReleaseService(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.transactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        this.transactionTemplate.setReadOnly(true);

    }

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
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            public void doInTransactionWithoutResult(TransactionStatus status) {
                try{
                    createTickets(release);
                    assignTicketsToRelease(release.getId(), release.getTickets());
                } catch (NoSuchElementException exception) {
                    exception.printStackTrace();
                    status.setRollbackOnly();
                }

            }
        });

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
