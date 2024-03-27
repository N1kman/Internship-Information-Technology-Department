package com.crud.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientFlight {
	
	private Client client = null;
	
	private Flight flight = null;

	public ClientFlight(Long clientId, Long flightId) {
		this.client = new Client();
		this.flight = new Flight();
		this.client.setId(clientId);
		this.flight.setId(flightId);
	}
}
