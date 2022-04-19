package com.ewolff.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareError;
import org.aspectj.lang.annotation.DeclareWarning;

@Aspect
public class DeclareErrorAspect {

	@DeclareError("(call (* java.sql..*.*(..)) || call (* javax.sql..*.*(..)))" +
			"&& !within(com.ewolff..repository..*)")
	public static final String jdbcOnlyInRepository = "JDBC may only be used in Repositories!";

	
	@DeclareWarning("call (* java.lang.Exception+.printStackTrace(..))")
	public static final String noPrintStackTrace = "no printStackTrace() - log exception please!";
	
}
