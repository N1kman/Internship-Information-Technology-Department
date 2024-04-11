package com.backend.airport.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.airport.dto.ReservationDTO;
import com.backend.airport.entity.Reservation;
import com.backend.airport.mapper.ReservationMapper;
import com.backend.airport.service.ReservationService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	private final ReservationService reservationService;
	private final ReservationMapper reservationMapper;

	@Autowired
	public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper) {
		this.reservationService = reservationService;
		this.reservationMapper = reservationMapper;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReservationDTO> get(@PathVariable Long id) {
		return new ResponseEntity<>(reservationMapper.toDTO(reservationService.getReservation(id)), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Set<ReservationDTO>> getAll() {
		List<Reservation> list = reservationService.getReservations();
		if (list.isEmpty()) {
			throw new EntityNotFoundException("Table is clear");
		}
		return new ResponseEntity<>(reservationMapper.toDTOs(new HashSet<>(list)), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ReservationDTO> delete(@PathVariable Long id) {
		return new ResponseEntity<>(reservationMapper.toDTO(reservationService.deleteReservation(id)), HttpStatus.OK);
	}

	@PostMapping("/{id}")
	public ResponseEntity<ReservationDTO> post(@PathVariable Long id, @RequestBody ReservationDTO reservation) {
		return new ResponseEntity<>(
				reservationMapper
						.toDTO(reservationService.addReservation(id, reservationMapper.toReservation(reservation))),
				HttpStatus.OK);
	}
}
