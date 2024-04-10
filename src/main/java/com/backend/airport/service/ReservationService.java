package com.backend.airport.service;

import java.util.List;

import com.backend.airport.entity.Reservation;

public interface ReservationService {
	
	Reservation getReservation(Long id);
	
	List<Reservation> getReservations();
	
	Reservation deleteReservation(Long id);
	
	Reservation addReservation(Long id, Reservation reservation);
}
