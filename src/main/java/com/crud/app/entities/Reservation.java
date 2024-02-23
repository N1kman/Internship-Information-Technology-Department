package com.crud.app.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	
	@EqualsAndHashCode.Exclude
	private Long id;
	
	Date ReservationDate;
	
	@EqualsAndHashCode.Exclude
	private Ticket Ticket;
	
	@EqualsAndHashCode.Exclude
	private Client Client;
}
