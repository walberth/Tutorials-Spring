package com.project.xxxxx.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private int Id;
    private String Name;
    private List<Permission> Permissions;

    @Override
    public String toString() {
        return "Role{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Permissions=" + Permissions +
                '}';
    }
}
