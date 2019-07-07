package com.managment.security.service.implementation;

import com.managment.security.repository.ISessionRepository;
import com.managment.security.service.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SessionService")
public class SessionService implements ISessionService {

    @Autowired
    private ISessionRepository sessionRepository;
}
