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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.airport.dto.FlightDTO;
import com.backend.airport.entity.Flight;
import com.backend.airport.mapper.FlightMapper;
import com.backend.airport.service.FlightService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/flights")
public class FlightController {

	private final FlightService flightService;
	private final FlightMapper flightMapper;

	@Autowired
	public FlightController(FlightService flightService, FlightMapper flightMapper) {
		this.flightService = flightService;
		this.flightMapper = flightMapper;
	}

	@GetMapping("/{id}")
	public ResponseEntity<FlightDTO> get(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(flightMapper.toDTO(flightService.getFlight(id)), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	public ResponseEntity<Set<FlightDTO>> getAll() {
		try {
			List<Flight> list = flightService.getFlights();
			if (list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return new ResponseEntity<>(flightMapper.toDTOs(new HashSet<>(list)), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<FlightDTO> delete(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(flightMapper.toDTO(flightService.deleteFlight(id)), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping
	public ResponseEntity<FlightDTO> post(@RequestBody FlightDTO flight) {
		try {
			return new ResponseEntity<>(flightMapper.toDTO(flightService.addFlight(flightMapper.toFlight(flight))),
					HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping
	public ResponseEntity<FlightDTO> put(@RequestBody FlightDTO flight) {
		try {
			return new ResponseEntity<>(flightMapper.toDTO(flightService.updateFlight(flightMapper.toFlight(flight))),
					HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
