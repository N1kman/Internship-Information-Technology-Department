package com.crud.app.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
	@EqualsAndHashCode.Exclude
	private Long id;
	
	private String code;
	
	private String placeDeparture;
	
	private String placeArrival;
	
	private LocalDateTime dateDeparture;
	
	private LocalDateTime dateArrival;

	@EqualsAndHashCode.Exclude
	private Aircraft aircraft;
	
	//@EqualsAndHashCode.Exclude
	//private Set<Client> clients = new HashSet<>();
	
	//@EqualsAndHashCode.Exclude
	//private Set<Ticket> tickets = new HashSet<>();
}
