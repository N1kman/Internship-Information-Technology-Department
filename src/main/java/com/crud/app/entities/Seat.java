package com.crud.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
	
	@EqualsAndHashCode.Exclude
	private Long id;
	
	private String seatNumber;
	
	private String seatType;
	
	private Long aircraftId;
	
	//@EqualsAndHashCode.Exclude
	//private Aircraft aircraft;
	
	//@EqualsAndHashCode.Exclude
	//private Set<Ticket> tickets = new HashSet<>();
}
