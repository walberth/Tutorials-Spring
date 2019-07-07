package com.managment.security.repository.implementation;

import com.managment.security.repository.ISecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("SecurityRepository")
public class SecurityRepository implements ISecurityRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
