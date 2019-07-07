package com.managment.security.controller;

import com.managment.security.service.IUserService;
import com.managment.security.transversal.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/validateusername")
    public Response<Boolean> ValidateUsername(@RequestParam(value="username") String username) {
        return userService.ValidateUsername(username);
    }
}
