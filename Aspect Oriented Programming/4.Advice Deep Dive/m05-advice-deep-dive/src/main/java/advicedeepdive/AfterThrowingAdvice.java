package advicedeepdive;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterThrowingAdvice {

  Logger logger = LoggerFactory.getLogger(AfterThrowingAdvice.class);

  private boolean afterThrowingCalled = false;

  public void reset() {
    afterThrowingCalled = false;
  }

  public boolean isAfterThrowingCalled() {
    return afterThrowingCalled;
  }

  @AfterThrowing(pointcut = "execution(void throwsRuntimeException())", throwing = "ex")
  public void logException(RuntimeException ex) {
    afterThrowingCalled = true;
    logger.error("Exception ", ex);
  }

}
