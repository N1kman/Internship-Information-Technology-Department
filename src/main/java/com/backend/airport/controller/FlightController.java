package com.backend.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.backend.airport.DTO.FlightDTO;
import com.backend.airport.entity.Flight;
import com.backend.airport.mapper.FlightMapper;
import com.backend.airport.service.FlightService;

@RestController
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private FlightMapper flightMapper;
	
	@GetMapping("/flight/{id}")
	public FlightDTO greeting(@PathVariable Long id) {
		Flight flight = flightService.getFlight(id);
		return flightMapper.toDTO(flight);
	}
}
