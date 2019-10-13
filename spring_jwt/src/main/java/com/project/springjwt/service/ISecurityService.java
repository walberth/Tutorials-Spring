package com.project.springjwt.service;

import com.project.springjwt.model.JwtRequest;
import com.project.springjwt.model.JwtResponse;
import com.project.springjwt.model.Person;
import com.project.springjwt.model.User;
import com.project.springjwt.transversal.Response;

public interface ISecurityService {
    Response<JwtResponse> authenticate(JwtRequest request);
    Response<JwtResponse> refresh(String token);
    Response<User> create(Person person, String username, String password);
}
