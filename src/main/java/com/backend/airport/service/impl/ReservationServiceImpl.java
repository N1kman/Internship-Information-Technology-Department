package com.backend.airport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.airport.entity.Client;
import com.backend.airport.entity.Reservation;
import com.backend.airport.entity.Ticket;
import com.backend.airport.repository.ClientRepository;
import com.backend.airport.repository.ReservationRepository;
import com.backend.airport.repository.TicketRepository;
import com.backend.airport.service.ReservationService;

import jakarta.persistence.EntityExistsException;

@Service
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository reservationRepository;
	private final TicketRepository ticketRepository;
	private final ClientRepository clientRepository;

	@Autowired
	public ReservationServiceImpl(ReservationRepository reservationRepository, TicketRepository ticketRepository,
			ClientRepository clientRepository) {
		this.reservationRepository = reservationRepository;
		this.ticketRepository = ticketRepository;
		this.clientRepository = clientRepository;
	}

	@Override
	public Reservation getReservation(Long id) {
		return reservationRepository.getById(id);
	}

	@Override
	public List<Reservation> getReservations() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation deleteReservation(Long id) {
		Reservation reservation = reservationRepository.getById(id);
		reservation.getTicket().setTicketStatus(false);
		reservation.getTicket().removeReservation(reservation);
		reservation.getClient().removeReservation(reservation);
		reservationRepository.deleteById(id);
		return reservation;
	}

	@Override
	public Reservation addReservation(Long id, Reservation reservation) {
		Ticket ticket = ticketRepository.getById(id);
		if (Boolean.TRUE.equals(ticket.getTicketStatus())) {
			throw new EntityExistsException("Reservated");
		}
		ticket.setTicketStatus(true);
		Client client = clientRepository.getByPassportId(reservation.getClient().getPassportId());
		if (client == null) {
			client = reservation.getClient();
		}
		reservation.setClient(client);
		reservation.setTicket(ticket);

		return reservationRepository.save(reservation);
	}

}
