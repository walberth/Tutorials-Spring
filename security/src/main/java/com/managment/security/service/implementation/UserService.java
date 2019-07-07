package com.managment.security.service.implementation;

import com.managment.security.repository.IUserRepository;
import com.managment.security.service.IUserService;
import com.managment.security.transversal.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Response<Boolean> ValidateUsername(String username) {
        Response<Boolean> response = new Response<>();

        if(username.isEmpty()) {
            response.setMessage("Ocurrio un error");

            return response;
        }

        boolean exist = userRepository.ValidateUsername(username);

        response.setData(exist);
        response.setIsWarning(false);

        return response;
    }
}
