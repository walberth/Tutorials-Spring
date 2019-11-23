package com.project.loggingdoitright.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String uriMethod = request.getRequestURI();
        int index = uriMethod.lastIndexOf('/') + 1;
        String action = uriMethod.substring(index, uriMethod.length());

        System.setProperty("callSite", request.getRequestURI());
        System.setProperty("action", action);
        System.setProperty("machineName", InetAddress.getLocalHost().getHostName());
        System.setProperty("httpMethod", request.getMethod());

        if(request.getMethod().equals("GET")) {
            String queryString = request.getQueryString();

            if(queryString != null) {
                System.setProperty("payload", queryString);
            }
        }

        String username = request.getParameter("username");

        if(username != null) {
            System.setProperty("username", username);
        }else {
            System.setProperty("username", "DEFAULT");
        }

        return true;
    }
}

