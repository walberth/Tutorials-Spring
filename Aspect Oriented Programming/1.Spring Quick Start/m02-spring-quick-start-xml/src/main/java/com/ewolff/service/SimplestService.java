package com.ewolff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ewolff.domain.DomainObject;
import com.ewolff.repository.SimpleRepository;

@Service
public class SimplestService {

	@Autowired
	SimpleRepository repository;

	public DomainObject service() {
		return repository.findDomainObject();
	}
}
