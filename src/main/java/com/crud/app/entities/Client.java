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
public class Client {
	
	@EqualsAndHashCode.Exclude
	private Long id;
	
	String passportId;
	
	String firstname;
	
	String surname;
	
	String patronymic;
	
	@EqualsAndHashCode.Exclude
	private Set<Flight> flights = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	private Set<Reservation> reservation = new HashSet<>();
}
