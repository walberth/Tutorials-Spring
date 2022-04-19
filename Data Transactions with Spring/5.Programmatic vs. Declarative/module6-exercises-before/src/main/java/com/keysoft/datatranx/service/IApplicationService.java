package com.keysoft.datatranx.service;


import com.keysoft.datatranx.model.Application;

import java.util.List;

public interface IApplicationService {
    List<Application> getAllApplications();
    void addApplication(Application application);
    Application getApplicationById(int applicationId);
    void updateApplication(Application application);
    void deleteApplication(int applicationId);
}
