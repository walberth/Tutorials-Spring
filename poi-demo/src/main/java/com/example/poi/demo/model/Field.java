package com.example.poi.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Field {
    private String name;
    private String description;
    private Type type;

    public enum Type {
        STRING, DATE, DOUBLE, INTEGER;
    }
}
