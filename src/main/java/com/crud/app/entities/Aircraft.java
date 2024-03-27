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
public class Aircraft {

	public Aircraft(Aircraft aircraft) {
		this.id = aircraft.id;
		this.registrationNumber = aircraft.registrationNumber;
		this.model = aircraft.model;
		this.company = aircraft.company;
		if(aircraft.getSeats() != null) {
			this.seats = new HashSet<>(aircraft.getSeats());
		}
	}

	@EqualsAndHashCode.Exclude
	private Long id;
	
	private String registrationNumber;
	
	private String model;
	
	private String company;
	
	@EqualsAndHashCode.Exclude
	private Set<Seat> seats = null;
	
	//@EqualsAndHashCode.Exclude
	//private Set<Flight> flights = new HashSet<>();
}
