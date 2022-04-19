package com.pluralsight.service;

import java.util.List;

import com.pluralsight.model.Ride;

public interface RideService {

	Ride createRide(Ride ride);
	
	List<Ride> getRides();
	
	Ride getRide(Integer id);

	Ride updateRide(Ride ride);

	void batch();

}