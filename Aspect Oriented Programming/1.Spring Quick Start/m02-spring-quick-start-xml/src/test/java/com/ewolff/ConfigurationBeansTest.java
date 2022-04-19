package com.ewolff;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ewolff.domain.DomainObject;
import com.ewolff.service.SimplestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class ConfigurationBeansTest {

	@Autowired
	private SimplestService simpleService;

	@Test
	public void dependencyInjectionShouldWork() {
		assertNotNull(simpleService);
	}

	@Test
	public void serviceShouldReturnDomainObject() {
		assertThat(simpleService.service(), equalTo(new DomainObject()));
	}

}
