package com.project.xxxxx.repository;

import com.project.xxxxx.model.Session;
import java.time.LocalDateTime;

public interface ISessionRepository {
    Integer createSession(Session session);
    void updateSession(String username, String token, LocalDateTime expirationTime);
    void deleteSession(String username);
}
