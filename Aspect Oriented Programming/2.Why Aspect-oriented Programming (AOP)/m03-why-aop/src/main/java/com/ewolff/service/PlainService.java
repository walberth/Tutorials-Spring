package com.ewolff.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class PlainService {

	private static final String CLASSNAME = PlainService.class.getName();

	Logger logger = LoggerFactory.getLogger(PlainService.class);

	@Autowired
	private PlatformTransactionManager transactionManager;

	public void doSomething() {
		final String METHODNAME = "doSomething";
		logger.trace("entering " + CLASSNAME + "." + METHODNAME);
		TransactionStatus tx = transactionManager
				.getTransaction(new DefaultTransactionDefinition());
		try {
			// Business Logic
		} catch (RuntimeException ex) {
			logger.error("exception in " + CLASSNAME + "." + METHODNAME, ex);
			tx.setRollbackOnly();
			throw ex;
		} finally {
			transactionManager.commit(tx);
			logger.trace("exiting " + CLASSNAME + "." + METHODNAME);
		}
	}

}
