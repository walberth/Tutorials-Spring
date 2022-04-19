package com.ewolff.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ExceptionLoggingAspect extends CallTracker {

	Logger logger = LoggerFactory.getLogger(ExceptionLoggingAspect.class);

	@AfterThrowing(pointcut = "SystemArchitecture.Repository() || SystemArchitecture.Service()", throwing = "ex")
	public void logException(Exception ex)  {
		trackCall();
		logger.error("Exception", ex);
	}

}
