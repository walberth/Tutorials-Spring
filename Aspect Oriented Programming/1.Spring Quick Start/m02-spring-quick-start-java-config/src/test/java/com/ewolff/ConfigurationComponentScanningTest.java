package com.ewolff;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ewolff.domain.DomainObject;
import com.ewolff.service.SimpleService;

import configuration.SystemConfigurationComponentScanning;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SystemConfigurationComponentScanning.class)
public class ConfigurationComponentScanningTest {

	@Autowired
	private SimpleService simpleService;

	@Test
	public void dependencyInjectionShouldWork() {
		assertNotNull(simpleService);
	}

	@Test
	public void serviceShouldReturnDomainObject() {
		assertThat(simpleService.service(), equalTo(new DomainObject()));
	}

}
