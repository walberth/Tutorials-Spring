package com.managment.security.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Login {
    private int IdUser;
    private String CompleteName;
    private boolean Sex;
    private String Email;
    private String Username;
    private String Password;
    private boolean Active;
    private String Document;
    private String Mobile;
    private String Telephone;
    private int Age;
    private Date BirthDate;
    private Session Session;
    private List<Role> UserRoles;
    private List<Permission> UserPermissions;

    //public double PersonAge() {
        //return  Math.floor((LocalDate.now() - this.BirthDate.toLocalDate())).TotalDays / 365);
    //}

    @Override
    public String toString() {
        return "Login{" +
                "idUser='" + IdUser + '\'' +
                ", completeName=" + CompleteName +
                ", sex=" + Sex +
                ", email=" + Email +
                ", username=" + Username +
                ", password=" + Password +
                ", active=" + Active +
                ", document=" + Document +
                ", mobile=" + Mobile +
                ", telephone=" + Telephone +
                ", age=" + Age +
                ", birthDate=" + BirthDate +
                ", session=" + Session +
                ", userRoles=" + UserRoles +
                ", userPermissions=" + UserPermissions +
                '}';
    }
}
