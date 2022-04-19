package com.keysoft.datatranx.service;


import com.keysoft.datatranx.model.Application;

import java.util.List;
import java.util.Optional;

public interface IApplicationService {
    List<Application> getAllApplications();
    void addApplication(Application application);
    Optional<Application> getApplicationById(int applicationId);
    void updateApplication(Application application);
    void deleteApplication(int applicationId);
}
