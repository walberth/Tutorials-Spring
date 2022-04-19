package simpleaspect;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/simpleaspect.xml")
public class SimpleAspectXMLTest {

	@Autowired
	TracingAspect tracingAspect;

	@Autowired
	SimpleService simpleService;

	@Test
	public void aspectIsCalled() {
		assertFalse(tracingAspect.isEnteringCalled());
		simpleService.doSomethingElse(42);
		assertTrue(tracingAspect.isEnteringCalled());
	}

}
