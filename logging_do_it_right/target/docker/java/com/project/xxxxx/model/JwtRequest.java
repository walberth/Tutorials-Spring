package com.project.xxxxx.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {
    private static final long serialVersionUID = -5616176897013108345L;
    private String username;
    private String password;
}
