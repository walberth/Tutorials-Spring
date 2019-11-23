package com.project.xxxxx.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private static final long serialVersionUID = 8317676219297719109L;
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
