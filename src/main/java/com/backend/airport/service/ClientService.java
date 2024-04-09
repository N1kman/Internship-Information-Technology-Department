package com.backend.airport.service;

import java.util.List;

import com.backend.airport.entity.Client;

public interface ClientService {
	
	Client getClient(Long id);
	
	List<Client> getClients();
	
	Client deleteClient(Long id);
	
	Client addClient(Client client);
	
	//Client updateClient(Client client);
}
