package com.keysoft.datatranx.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.keysoft.datatranx.model.Application;

import java.util.List;


@Repository
public class ApplicationDAO implements IApplicationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Application> getAllApplications() {
        String query = "select a from Application a order by a.name";
        return (List<Application>) entityManager.createQuery(query).getResultList();

    }

    @Override
    public void addApplication(Application application) {
        entityManager.persist(application);
    }

    @Override
    public Application getApplicationById(int applicationId) {
        return entityManager.find(Application.class, applicationId);
    }

    @Override
    public void updateApplication(Application application) {
        Application app = getApplicationById(application.getId());
        app.setName(application.getName());
        app.setDescription(application.getDescription());
        app.setOwner(application.getOwner());
        entityManager.flush();
    }

    @Override
    public void deleteApplication(int applicationId) {
        entityManager.remove(getApplicationById(applicationId));
    }

}
