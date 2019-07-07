package com.managment.security.service;

import com.managment.security.transversal.Response;

public interface IUserService {
    Response<Boolean> ValidateUsername(String username);
}
