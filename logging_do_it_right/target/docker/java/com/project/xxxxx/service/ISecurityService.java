package com.project.xxxxx.service;

import com.project.xxxxx.model.JwtRequest;
import com.project.xxxxx.model.JwtResponse;
import com.project.xxxxx.model.Person;
import com.project.xxxxx.model.User;
import com.project.xxxxx.transversal.Response;

public interface ISecurityService {
    void CreatePerson(Person person, Person person2);
    void UpdatePerson(Person person);
    void DeletePerson();
    Response<JwtResponse> authenticate(JwtRequest request);
    Response<JwtResponse> refresh(String token);
    Response<User> create(Person person, String username, String password);
}
