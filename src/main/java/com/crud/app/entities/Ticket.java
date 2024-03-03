package com.crud.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
	
	@EqualsAndHashCode.Exclude
	private Long id;
	
	String ticketNumber;
	
	Boolean ticketStatus;
	
	//@EqualsAndHashCode.Exclude
	//private Flight flight;
	
	@EqualsAndHashCode.Exclude
	private Seat seat;
	
	//@EqualsAndHashCode.Exclude
	//private Reservation reservation;

}
