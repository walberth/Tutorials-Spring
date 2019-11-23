package com.project.loggingdoitright.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.project.loggingdoitright.service.implementation.*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.setProperty("methodName", joinPoint.getSignature().getName());
    }
}
