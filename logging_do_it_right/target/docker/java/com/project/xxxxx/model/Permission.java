package com.project.xxxxx.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private int Id;
    private String Name;

    @Override
    public String toString() {
        return "Permission{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
