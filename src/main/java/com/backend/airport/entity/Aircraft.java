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
public class Aircraft {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Exclude
	private Long id;
	
	private String registrationNumber;
	
	private String model;
	
	private String company;
	
	@EqualsAndHashCode.Exclude
	private Set<Seat> seats;
	
	@EqualsAndHashCode.Exclude
	private Set<Flight> flights;
}
