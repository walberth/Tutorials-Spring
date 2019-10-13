package com.project.springjwt.repository;

import com.project.springjwt.model.User;

public interface IUserRepository {
    User getUserInformation(String username, String password);
    User getUser(String username);
    User createUser(User user);
    Integer validateUserExists(String username);
}
