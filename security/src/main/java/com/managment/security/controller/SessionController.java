package com.managment.security.controller;

import com.managment.security.service.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/session")
public class SessionController {
    @Autowired
    private ISessionService sessionService;
}
