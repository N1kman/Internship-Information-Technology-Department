package com.backend.airport.service;

import java.util.List;

import com.backend.airport.entity.Flight;

public interface FlightService {
	
	Flight getFlight(Long id);
	
	List<Flight> getFlights();
}
