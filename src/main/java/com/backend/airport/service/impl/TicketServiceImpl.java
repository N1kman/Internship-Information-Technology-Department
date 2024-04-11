package com.backend.airport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.airport.entity.Flight;
import com.backend.airport.entity.Ticket;
import com.backend.airport.repository.FlightRepository;
import com.backend.airport.repository.SeatRepository;
import com.backend.airport.repository.TicketRepository;
import com.backend.airport.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	private final TicketRepository ticketRepository;
	private final SeatRepository seatRepository;
	private final FlightRepository flightRepository;

	@Autowired
	public TicketServiceImpl(TicketRepository ticketRepository, SeatRepository seatRepository,
			FlightRepository flightRepository) {
		this.ticketRepository = ticketRepository;
		this.seatRepository = seatRepository;
		this.flightRepository = flightRepository;
	}

	@Override
	public Ticket getTicket(Long id) {
		return ticketRepository.getById(id);
	}

	@Override
	public List<Ticket> getTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket deleteTicket(Long id) {
		Ticket ticket = ticketRepository.getById(id);
		ticketRepository.delete(ticket);
		return ticket;
	}

	@Override
	public Ticket addTicket(Long id, Ticket ticket) {
		ticket.setSeat(seatRepository.getById(ticket.getSeat().getId()));
		Flight flight = flightRepository.getById(id);
		ticket.setFlight(flight);
		return ticketRepository.save(ticket);
	}
}
