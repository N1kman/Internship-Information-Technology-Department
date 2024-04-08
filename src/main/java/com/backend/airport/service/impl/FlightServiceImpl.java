package com.backend.airport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.airport.entity.Flight;
import com.backend.airport.repository.FlightRepository;
import com.backend.airport.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService  {
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Override 
	public Flight getFlight(Long id) {
		return flightRepository.findById(id).get();
	}

}
