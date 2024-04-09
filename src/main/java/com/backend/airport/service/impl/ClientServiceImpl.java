package com.backend.airport.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.airport.entity.Client;
import com.backend.airport.entity.Reservation;
import com.backend.airport.repository.ClientRepository;
import com.backend.airport.repository.ReservationRepository;
import com.backend.airport.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public Client getClient(Long id) {
		return clientRepository.getById(id);
	}

	@Override
	public List<Client> getClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client deleteClient(Long id) {
		Client client = clientRepository.getById(id);
		List<Reservation> res = new ArrayList<>(client.getReservations());
		reservationRepository.deleteAll(res);
		clientRepository.deleteById(client.getId());
//		for (Reservation reservation : client.getReservations()) {
//		    reservationRepository.delete(reservation);
//		}

		clientRepository.delete(client);
		return client;
	}

	@Override
	public Client addClient(Client client) {
		return clientRepository.save(client);
	}
}
