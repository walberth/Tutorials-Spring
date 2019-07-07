package com.managment.security.repository.implementation;

import com.managment.security.repository.ISessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("SessionRepository")
public class SessionRepository implements ISessionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
