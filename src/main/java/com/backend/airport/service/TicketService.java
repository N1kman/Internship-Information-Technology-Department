package com.backend.airport.service;

import java.util.List;

import com.backend.airport.entity.Ticket;

public interface TicketService {
	
	Ticket getTicket(Long id);
	
	List<Ticket> getTickets();
	
	Ticket deleteTicket(Long id);
	
	Ticket addTicket(Long id, Ticket ticket);
}
