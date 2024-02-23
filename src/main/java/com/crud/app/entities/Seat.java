package com.crud.app.entities;

import java.util.HashSet;
import java.util.Set;

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
	
	String seatNumber;
	
	String seatType;
	
	@EqualsAndHashCode.Exclude
	private Aircraft aircraft;
	
	@EqualsAndHashCode.Exclude
	private Set<Ticket> tickets = new HashSet<>();
}
