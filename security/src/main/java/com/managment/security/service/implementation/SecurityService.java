package com.managment.security.service.implementation;

import com.managment.security.repository.ISecurityRepository;
import com.managment.security.service.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SecurityService")
public class SecurityService implements ISecurityService {

    @Autowired
    private ISecurityRepository securityRepository;
}
