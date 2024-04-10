package com.backend.airport.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.backend.airport.dto.TicketDTO;
import com.backend.airport.entity.Ticket;
import com.backend.airport.mapper.TicketMapper;
import com.backend.airport.service.TicketService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	private final TicketService ticketService;
	private final TicketMapper ticketMapper;
	
	@Autowired
	public TicketController(TicketService ticketService, TicketMapper ticketMapper) {
		this.ticketService = ticketService;
		this.ticketMapper = ticketMapper;
	}

	@GetMapping("/{id}")
	public ResponseEntity<TicketDTO> get(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(ticketMapper.toDTO(ticketService.getTicket(id)), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping
	public ResponseEntity<Set<TicketDTO>> getAll() {
		try {
			List<Ticket> list = ticketService.getTickets();
			if (list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return new ResponseEntity<>(ticketMapper.toDTOs(new HashSet<>(list)), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<TicketDTO> delete(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(ticketMapper.toDTO(ticketService.deleteTicket(id)), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/* id - reference to Flight */
	@PostMapping("/{id}")
	public ResponseEntity<TicketDTO> post(@PathVariable Long id, @RequestBody TicketDTO ticket) {
		try {
			return new ResponseEntity<>(ticketMapper.toDTO(ticketService.addTicket(id, ticketMapper.toTicket(ticket))),
					HttpStatus.OK);
		} catch (EntityNotFoundException | DataIntegrityViolationException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping
	public ResponseEntity<TicketDTO> put(@RequestBody TicketDTO ticket) {
		try {
			return new ResponseEntity<>(ticketMapper.toDTO(ticketService.updateTicket(ticketMapper.toTicket(ticket))),
					HttpStatus.OK);
		} catch (EntityNotFoundException | DataIntegrityViolationException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
