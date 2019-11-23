package com.project.xxxxx.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int Id;
    private String FirstName;
    private String FatherLastName;
    private String MotherLastName;
    private Boolean Sex;
    private LocalDate BirthDate;
    private String Document;
    private String Email;
    private String Mobile;
    private String Telephone;
    private String UserRegister;
    private LocalDateTime TimeStamp;

    @Override
    public String toString() {
        return "Person{" +
                "Id=" + Id +
                ", FirstName='" + FirstName + '\'' +
                ", FatherLastName='" + FatherLastName + '\'' +
                ", MotherLastName='" + MotherLastName + '\'' +
                ", Sex=" + Sex +
                ", BirthDate=" + BirthDate +
                ", Document='" + Document + '\'' +
                ", Email='" + Email + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", UserRegister='" + UserRegister + '\'' +
                ", TimeStamp=" + TimeStamp +
                '}';
    }
}
