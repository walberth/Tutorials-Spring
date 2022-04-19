package com.ps.springm1.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.ps.springm1.Account;
import com.ps.springm1.Card;

public class CurrentAccount implements Account {
	
	@Autowired
	private Card creditCard;

	public void setCreditCard(Card creditCard) {
		this.creditCard = creditCard;
	}

	@Override
	public String createAccount() {
		// TODO Auto-generated method stub
		return "Current Account Created Successfully.";
	}

	@Override
	public String cardDetails() {
		// TODO Auto-generated method stub
		return this.creditCard.cardDetails();
	}
	
	public void onInit() {
		System.out.println("onInit method of CurrentAccount has been invoked.");
	}
	
	public void onDestroy() {
		System.out.println("onDestroy method of CurrentAccount has been invoked.");
	}
	
}
