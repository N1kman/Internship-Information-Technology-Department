package com.crud.app.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	
	String code;
	
	String placeDeparture;
	
	String placeArrival;
	
	Date dateDeparture;
	
	Date dateArrival;

	@EqualsAndHashCode.Exclude
	private Aircraft aircraft;
	
	@EqualsAndHashCode.Exclude
	private Set<Client> clients = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	private Set<Ticket> tickets = new HashSet<>();
}
