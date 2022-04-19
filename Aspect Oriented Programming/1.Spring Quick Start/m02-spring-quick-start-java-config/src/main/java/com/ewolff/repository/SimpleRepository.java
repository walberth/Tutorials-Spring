package com.ewolff.repository;

import org.springframework.stereotype.Repository;

import com.ewolff.domain.DomainObject;

@Repository
public class SimpleRepository {
	
	public DomainObject findDomainObject() {
		return new DomainObject();		
	}

}
