package com.example.poi.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColumnInformation {
    private String descriptionSql;
    private String descriptionField;
    private String fieldType;
    private int order;
}
