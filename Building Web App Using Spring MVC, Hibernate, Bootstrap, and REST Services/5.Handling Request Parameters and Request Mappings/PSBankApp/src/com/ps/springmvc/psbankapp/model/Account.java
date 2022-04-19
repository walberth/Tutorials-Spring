package com.ps.springmvc.psbankapp.model;

public class Account {
	private Integer accountNo;
	private String accountHolderName;
	private Integer balance;
	
	public Account() {
		accountNo = 0;
		accountHolderName = "";
		balance = 0;
	}
	
	public Account(Integer accNo, String holderName, Integer balance) {
		this.accountNo = accNo;
		this.accountHolderName = holderName;
		this.balance = balance;
	}

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
	
}
