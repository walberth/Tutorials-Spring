package com.ewolff.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TraceAnnotationAspect {
	
	Logger logger = LoggerFactory.getLogger(TraceAnnotationAspect.class);
	private int called=0;

	@Around("execution(@annotation.Trace * *(..))")
	public void trace(ProceedingJoinPoint proceedingJP ) throws Throwable {
		String methodInformation = 
				proceedingJP.getStaticPart().getSignature().getName();
		logger.trace("Entering "+methodInformation);
		called++;
		try {
			proceedingJP.proceed();
		} catch (Throwable ex) {
			logger.error("Exception in "+methodInformation, ex);
			throw ex;
		} finally {
			logger.trace("Exiting "+methodInformation);
		}
	}

	public void resetCalled() {
		called=0;
	}
	
	public int getCalled() {
		return called;
	}

}
