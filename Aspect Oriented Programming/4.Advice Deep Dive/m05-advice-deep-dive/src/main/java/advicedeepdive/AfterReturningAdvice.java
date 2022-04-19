package advicedeepdive;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterReturningAdvice {

	Logger logger = LoggerFactory.getLogger(AfterReturningAdvice.class);

	private boolean afterReturningCalled = false;

	public void reset() {
		afterReturningCalled = false;
	}

  public boolean isAfterReturningCalled() {
    return afterReturningCalled;
  }

  @AfterReturning(pointcut = "execution(* *(..))", returning = "string")
	public void logResult(String string) {
		afterReturningCalled = true;
		logger.trace("result " + string);
	}

}
