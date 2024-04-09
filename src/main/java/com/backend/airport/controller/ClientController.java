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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.airport.DTO.ClientDTO;
import com.backend.airport.entity.Client;
import com.backend.airport.mapper.ClientMapper;
import com.backend.airport.service.ClientService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientMapper clientMapper;
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> get(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(clientMapper.toDTO(clientService.getClient(id)), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<Set<ClientDTO>> getAll() {
		try {
			List<Client> list = clientService.getClients();
			if (list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return new ResponseEntity<>(clientMapper.toDTOs(new HashSet<>(list)), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ClientDTO> delete(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(clientMapper.toDTO(clientService.deleteClient(id)), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
