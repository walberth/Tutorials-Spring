package com.keysoft.datatranx.dao;

import com.keysoft.datatranx.model.Release;
import com.keysoft.datatranx.model.Ticket;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ReleaseDAO implements IReleaseDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRelease(Release release) {
        entityManager.persist(release);
    }

    @Override
    public Release getReleaseId(int releaseId) {
        return entityManager.find(Release.class, releaseId);
    }

}
