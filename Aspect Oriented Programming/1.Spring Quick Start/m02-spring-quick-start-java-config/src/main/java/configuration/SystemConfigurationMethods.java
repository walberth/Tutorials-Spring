package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ewolff.repository.SimpleRepository;
import com.ewolff.service.SimpleService;

@Configuration
public class SystemConfigurationMethods {

	@Bean
	public SimpleService simpleService() {
		return new SimpleService();
	}
	
	@Bean
	public SimpleRepository simpleRepository() {
		return new SimpleRepository();
	}
	
}
