package com.backend.airport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.airport.entity.Flight;
import com.backend.airport.entity.Ticket;
import com.backend.airport.repository.FlightRepository;
import com.backend.airport.repository.ReservationRepository;
import com.backend.airport.repository.SeatRepository;
import com.backend.airport.repository.TicketRepository;
import com.backend.airport.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
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
		if(ticket.getReservation() != null) {
			reservationRepository.delete(ticket.getReservation());
		}
		ticketRepository.deleteById(ticket.getId());
		return ticket;
	}

	@Override
	public Ticket addTicket(Long id, Ticket ticket) {
		ticket.setSeat(seatRepository.getById(ticket.getSeat().getId()));
		Flight flight = flightRepository.getById(id);
		ticket.setFlight(flight);
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		Ticket state = ticketRepository.getById(ticket.getId());
		ticket.setFlight(state.getFlight());
		if(ticket.getSeat() == null || ticket.getSeat().getId() == state.getSeat().getId()) {
			ticket.setSeat(state.getSeat());
		} else {
			ticket.setSeat(seatRepository.getById(ticket.getSeat().getId()));
		} 
		return ticketRepository.save(ticket);
	}
}
