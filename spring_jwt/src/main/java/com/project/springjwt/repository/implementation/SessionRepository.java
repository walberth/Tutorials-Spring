package com.project.springjwt.repository.implementation;

import com.project.springjwt.model.Session;
import com.project.springjwt.repository.ISessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository("SessionRepository")
public class SessionRepository implements ISessionRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer createSession(Session session) {
        return jdbcTemplate.queryForObject(String.format("CALL createSession(%s, '%s', %s, '%s')",
                session.getIdUser(),
                session.getToken(),
                session.getTimeToRelease(),
                session.getExpirationTime()), Integer.class);
    }

    @Override
    public void updateSession(String username, String token, LocalDateTime expirationTime) {
        jdbcTemplate.execute(String.format("CALL updateSession('%s', '%s', '%s')",
                username,
                token,
                expirationTime));
    }

    @Override
    public void deleteSession(String username) {
        jdbcTemplate.execute(String.format("CALL deleteSession('%s')", username));
    }
}
