package com.wildbeancoffee.friends.services;

import com.wildbeancoffee.friends.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressService  extends CrudRepository<Address, Integer> {
}
