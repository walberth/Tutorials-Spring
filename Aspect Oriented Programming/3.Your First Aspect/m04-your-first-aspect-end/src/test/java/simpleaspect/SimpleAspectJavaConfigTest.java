package simpleaspect;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configuration.SimpleAspectConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleAspectConfiguration.class)
public class SimpleAspectJavaConfigTest {

	@Autowired
	TracingAspect tracingAspect;

	@Autowired
	SimpleService simpleService;

	@Test
	public void aspectIsCalled() {
		assertFalse(tracingAspect.isEnteringCalled());
		simpleService.doSomething();
		assertTrue(tracingAspect.isEnteringCalled());
	}

}
