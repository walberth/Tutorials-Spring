package com.managment.security.repository.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExistenceRowMapper implements RowMapper<Boolean> {
    @Override
    public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {
        Boolean exists = rs.getBoolean("exists");

        return exists;
    }
}
