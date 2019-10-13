package com.project.springjwt.controller;

import com.project.springjwt.transversal.Message;
import com.project.springjwt.transversal.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.UUID;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    Response<Object> ExceptionHandler(Exception e) {
        String idFailure = UUID.randomUUID().toString();

        System.setProperty("idRequest", idFailure);

        Response<Object> response = new Response<>();

        response.setMessage(String.format(Message.ErrorInesperado, idFailure));
        response.setIsWarning(false);
        response.setIsSuccess(false);

        return response;
    }
}
