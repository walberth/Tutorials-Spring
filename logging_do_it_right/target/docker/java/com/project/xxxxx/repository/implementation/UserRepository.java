package com.project.xxxxx.repository.implementation;

import com.project.xxxxx.model.User;
import com.project.xxxxx.repository.IUserRepository;
import com.project.xxxxx.transversal.Constant;
import com.project.xxxxx.transversal.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public class UserRepository implements IUserRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUserInformation(String username, String password) {
        try {
            return jdbcTemplate.queryForObject(String.format("CALL getUserInformation('%s', '%s')",
                    username,
                    password),
                    (rs, rowNum) ->
                            new User(rs.getInt("id"),
                                     rs.getString("username"),
                                     rs.getString("password"),
                                     rs.getInt("idPerson"),
                                     rs.getString("userRegister"),
                                     TimeHelper.convertToLocalDateTimeViaInstant(rs.getTimestamp("timeStamp")),
                                     rs.getBoolean("active"),
                                     rs.getString("role")));
        } catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public User getUser(String username) {
        try {
            return jdbcTemplate.queryForObject(String.format("CALL getUser('%s')",
                    username),
                    (rs, rowNum) ->
                            new User(rs.getString("username"),
                                     rs.getString("role")));
        } catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User createUser(User user) {
        try {
            return jdbcTemplate.queryForObject(String.format("CALL createUser('%s', '%s', %s, %s, %s, '%s')",
                    user.getUsername(),
                    user.getPassword(),
                    user.getIdPerson(),
                    Constant.UserDefaultRole,
                    Constant.UserDefaultActive,
                    user.getUserRegister()),
                    (rs, rowNum) ->
                            new User(rs.getInt("id"),
                                     rs.getString("username"),
                                     rs.getString("password"),
                                     rs.getInt("idPerson"),
                                     rs.getString("userRegister"),
                                     TimeHelper.convertToLocalDateTimeViaInstant(rs.getTimestamp("timeStamp")),
                                     rs.getBoolean("active"),
                                     rs.getString("role")));
        } catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Integer validateUserExists(String username) {
        return jdbcTemplate.queryForObject(String.format("CALL validateUserExists('%s')",username), Integer.class);
    }
}
