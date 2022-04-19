package com.ps.springmvc.psbankapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.springmvc.psbankapp.dao.AccountDAO;
import com.ps.springmvc.psbankapp.model.Account;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountDAO accountDAO;
	
	@Override
	@Transactional
	public boolean saveAccount(Account account) {
		return accountDAO.saveAccount(account);
	}

	@Override
	@Transactional
	public List<Account> getAccounts() {
		return accountDAO.getAccounts();
	}

	@Override
	@Transactional
	public Account getAccount(Integer accountNo) {
		return accountDAO.getAccount(accountNo);
	}

	@Override
	@Transactional
	public boolean deleteAccount(int accountNo) {
		return accountDAO.deleteAccount(accountNo);
	}

}
