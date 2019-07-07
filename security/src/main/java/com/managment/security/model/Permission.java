package com.managment.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Permission {
    private int Id;
    private String Name;

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + Id + '\'' +
                ", name=" + Name +
                '}';
    }
}
