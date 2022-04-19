import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.pluralsight"})
public class AppConfig {

	//@Bean(name = "customerService")
	//public CustomerService getCustomerService() {
		//CustomerServiceImpl service = new CustomerServiceImpl(getCustomerRepository());
		//CustomerServiceImpl service = new CustomerServiceImpl();
		//service.setCustomerRepository(getCustomerRepository());
		//return service;
	//}
	
	//@Bean(name = "customerRepository")
	//public CustomerRepository getCustomerRepository() {
	//	return new HibernateCustomerRepositoryImpl();
	//}
	
}
