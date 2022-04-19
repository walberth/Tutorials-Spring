package advicedeepdive;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AroundAdvice {
	
	Logger logger = LoggerFactory.getLogger(AroundAdvice.class);
	private boolean called;
	
	public void reset() {
		called=false;
	}

	@Around("execution(* *(..))")
	public Object trace(ProceedingJoinPoint proceedingJP ) throws Throwable {
		String methodInformation = 
				proceedingJP.getStaticPart().getSignature().toString();
		logger.trace("Entering "+methodInformation);
		called=true;
		try {
			return proceedingJP.proceed();
		} catch (Throwable ex) {
			logger.error("Exception in "+methodInformation, ex);
			throw ex;
		} finally {
			logger.trace("Exiting "+methodInformation);
		}
	}

	public boolean isCalled() {
		return called;
	}

	

}
