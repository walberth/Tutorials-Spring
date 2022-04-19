package com.keysoft.datatranx.service;

import com.keysoft.datatranx.dao.IApplicationDAO;
import com.keysoft.datatranx.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService implements IApplicationService {

    @Autowired
    private IApplicationDAO applicationDAO;

    @Override
    public List<Application> getAllApplications(){
        return applicationDAO.findAll();
    }

    @Override
    public void addApplication(Application application) {
        applicationDAO.save(application);
    }

    @Override
    public Optional<Application> getApplicationById(int applicationId) {
        return applicationDAO.findById((long)applicationId);
    }

    @Override
    public void updateApplication(Application updatedApplication) {
        Application currentApplication = getApplicationById(updatedApplication.getId().intValue()).get();
       currentApplication.setDescription(updatedApplication.getDescription());
       currentApplication.setName(updatedApplication.getName());
       currentApplication.setOwner(updatedApplication.getOwner());
    }

    @Override
    public void deleteApplication(int applicationId) {
        applicationDAO.delete(getApplicationById(applicationId).get());
    }
}
