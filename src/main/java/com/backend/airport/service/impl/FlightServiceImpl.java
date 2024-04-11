package com.backend.airport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.airport.entity.Flight;
import com.backend.airport.repository.AircraftRepository;
import com.backend.airport.repository.FlightRepository;
import com.backend.airport.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	private final FlightRepository flightRepository;
	private final AircraftRepository aircraftRepository;

	@Autowired
	public FlightServiceImpl(FlightRepository flightRepository, AircraftRepository aircraftRepository) {
		this.flightRepository = flightRepository;
		this.aircraftRepository = aircraftRepository;
	}

	@Override
	public Flight getFlight(Long id) {
		return flightRepository.getById(id);
	}

	@Override
	public List<Flight> getFlights() {
		return flightRepository.findAll();
	}

	@Override
	public Flight deleteFlight(Long id) {
		Flight flight = flightRepository.getById(id);
		flightRepository.delete(flight);
		return flight;
	}

	@Override
	public Flight addFlight(Flight flight) {
		flight.setAircraft(aircraftRepository.getById(flight.getAircraft().getId()));
		return flightRepository.save(flight);
	}

	@Override
	public Flight updateFlight(Flight flight) {
		Flight state = flightRepository.getById(flight.getId());
		if (flight.getAircraft() != null && state.getAircraft().getId().equals(flight.getAircraft().getId())) {
			state.setAircraft(aircraftRepository.getById(flight.getAircraft().getId()));
		}
		if(flight.getCode() != null) {
			state.setCode(flight.getCode());
		}
		if(flight.getFlightInfo() != null) {
			state.setFlightInfo(flight.getFlightInfo());
		}	
		return flightRepository.save(state);
	}
}
