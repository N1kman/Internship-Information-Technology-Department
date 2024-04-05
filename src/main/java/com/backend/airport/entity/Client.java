package com.backend.airport.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Exclude
	private Long id;
	
	private String passportId;
	
	private String firstname;
	
	private String surname;
	
	private String patronymic;
	
	@EqualsAndHashCode.Exclude
	private Set<Flight> flights;
	
	@EqualsAndHashCode.Exclude
	private Set<Reservation> reservation;
}
