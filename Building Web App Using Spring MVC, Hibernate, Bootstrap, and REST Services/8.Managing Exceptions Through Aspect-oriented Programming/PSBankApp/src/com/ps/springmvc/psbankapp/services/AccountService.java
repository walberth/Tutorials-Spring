package com.ps.springmvc.psbankapp.services;

import java.util.List;

import com.ps.springmvc.psbankapp.model.Account;

public interface AccountService {
	public boolean saveAccount(Account account);
	
	public List<Account> getAccounts();

	public Account getAccount(Integer accountNo);

	public boolean deleteAccount(int accountNo);
}
