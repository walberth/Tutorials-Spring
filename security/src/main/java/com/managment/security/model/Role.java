package com.managment.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role {
    private int IdRole;
    private String RoleName;

    @Override
    public String toString() {
        return "Role{" +
                "idRole='" + IdRole + '\'' +
                ", roleName=" + RoleName +
                '}';
    }
}
