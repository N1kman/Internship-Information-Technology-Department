package com.backend.airport.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.airport.DTO.FlightDTO;
import com.backend.airport.entity.Flight;
import com.backend.airport.mapper.FlightMapper;
import com.backend.airport.service.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private FlightMapper flightMapper;
	
	@GetMapping("/{id}")
	public FlightDTO get(@PathVariable Long id) {
		Flight flight = flightService.getFlight(id);
		return flightMapper.toDTO(flight);
	}
	
	@GetMapping
	public Set<FlightDTO> getAll() {
		return flightMapper.toDTOs(new HashSet<>(flightService.getFlights()));
	}
}
