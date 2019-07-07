package com.managment.security.transversal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    private T Data;
    private boolean IsSuccess = true;
    private boolean IsWarning = true;
    private String Message = "";
}
