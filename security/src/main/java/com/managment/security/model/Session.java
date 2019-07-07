package com.managment.security.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Session {
    private String ExpirationHours;
    private String IdSession;
    private int IdUser;
    private Date SessionExpirationTime;

    @Override
    public String toString() {
        return "Session{" +
                "expirationHours='" + ExpirationHours + '\'' +
                ", idSession=" + IdSession +
                ", idUser=" + IdUser +
                ", sessionExpirationTime=" + SessionExpirationTime +
                '}';
    }
}
