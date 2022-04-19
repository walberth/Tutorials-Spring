package com.ps.springmvc.psbankapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class PSBankLoggingAspect {

		private Logger logger = Logger.getLogger(getClass().getName());
		
		@Pointcut("execution(* com.ps.springmvc.psbankapp.controllers.*.*(..))")
		private void forControllersPackage() { }
		
		@Pointcut("execution(* com.ps.springmvc.psbankapp.services.*.*(..))")
		private void forServicesPackage() { }
		
		@Pointcut("execution(* com.ps.springmvc.psbankapp.dao.*.*(..))")
		private void forDAOPackage() { }
		
		@Pointcut("forControllersPackage() || forServicesPackage() || forDAOPackage()")
		private void forApplication() { }
		
		@Before("forApplication()")
		public void before(JoinPoint joinPoint) {
			String methodName = joinPoint.getSignature().toShortString();
			logger.info("Information: in @Before Advice: Calling the Method: " + methodName);
		}
		
		@AfterReturning(pointcut="forApplication()",returning="result")
		public void afterReturning(JoinPoint joinPoint, Object result) {
			String methodName = joinPoint.getSignature().toShortString();
			logger.info("Info: In @AfterReturning: From Method: " + methodName);
			logger.info("Info: Data Returned by Method: " + result);
		}
		
}
