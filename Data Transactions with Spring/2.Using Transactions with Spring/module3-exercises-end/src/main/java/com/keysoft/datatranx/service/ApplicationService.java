package com.keysoft.datatranx.service;

import com.keysoft.datatranx.dao.IApplicationDAO;
import com.keysoft.datatranx.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService implements IApplicationService {

    @Autowired
    private IApplicationDAO applicationDAO;

    @Override
    public List<Application> getAllApplications(){
        return applicationDAO.getAllApplications();
    }

    @Override
    public void addApplication(Application application) {
        applicationDAO.addApplication(application);
    }

    @Override
    public Application getApplicationById(int applicationId) {
        return applicationDAO.getApplicationById(applicationId);
    }

    @Override
    public void updateApplication(Application application) {
        applicationDAO.updateApplication(application);

    }

    @Override
    public void deleteApplication(int applicationId) {
        applicationDAO.deleteApplication(applicationId);
    }
}
