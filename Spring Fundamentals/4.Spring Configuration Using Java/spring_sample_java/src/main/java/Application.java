import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pluralsight.service.CustomerService;

public class Application {

	public static void main(String[] args) {
		
		ApplicationContext appContext = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		//CustomerService service = new CustomerServiceImpl();
		
		CustomerService service = 
				appContext.getBean("customerService", CustomerService.class);
		
		System.out.println(service.findAll().get(0).getFirstname());
	}

}
