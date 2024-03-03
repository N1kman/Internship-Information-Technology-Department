package com.crud.app.entities;

import java.time.LocalDateTime;

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
	
	LocalDateTime reservationDate;
	
	@EqualsAndHashCode.Exclude
	private Ticket ticket;
	
	//@EqualsAndHashCode.Exclude
	//private Client client;
}
