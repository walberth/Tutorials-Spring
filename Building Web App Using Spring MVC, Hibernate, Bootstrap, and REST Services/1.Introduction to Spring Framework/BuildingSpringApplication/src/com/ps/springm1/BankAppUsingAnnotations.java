package com.ps.springm1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BankAppUsingAnnotations {

	private static ApplicationContext context;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		context = new AnnotationConfigApplicationContext(BankAppConfig.class);
		Account account = context.getBean("currentAccount",Account.class);
		System.out.println(account.createAccount());
		System.out.println(account.cardDetails());
		

	}

}
