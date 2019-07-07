package com.managment.security.repository.implementation;

import com.managment.security.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public class UserRepository implements IUserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean ValidateUsername(String username) {
        String query = String.format("select * from users.vaidateUsername('%s')", username);
        String exists = jdbcTemplate.queryForObject(query, String.class);

        if(exists == null || exists.isEmpty()) {
            return false;
        }

        return Boolean.parseBoolean(exists);
    }
}
