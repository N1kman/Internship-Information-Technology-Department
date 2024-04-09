package com.backend.airport.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.airport.entity.Flight;
import com.backend.airport.repository.AircraftRepository;
import com.backend.airport.repository.FlightRepository;
import com.backend.airport.repository.ReservationRepository;
import com.backend.airport.repository.TicketRepository;
import com.backend.airport.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService  {
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private AircraftRepository aircraftRepository;
	
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
		reservationRepository.deleteAll(flight.getTickets().stream()
				.filter(ticket -> ticket.getReservation() != null)
			    .map(ticket -> ticket.getReservation())
			    .collect(Collectors.toList()));
		ticketRepository.deleteAll(new ArrayList<>(flight.getTickets()));
		flightRepository.deleteById(flight.getId());
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
		if(flight.getAircraft() == null || state.getAircraft().getId() == flight.getAircraft().getId()) {
			flight.setAircraft(state.getAircraft());
		} else {
			flight.setAircraft(aircraftRepository.getById(flight.getAircraft().getId()));
		}
		return flightRepository.save(flight);
	}

}
