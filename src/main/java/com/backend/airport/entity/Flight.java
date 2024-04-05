package com.backend.airport.entity;

import java.util.Set;

import jakarta.persistence.Embedded;
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
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Exclude
	private Long id;
	
	private String code;
	
	@Embedded
    private FlightInfo flightInfo = new FlightInfo();

	@EqualsAndHashCode.Exclude
	private Aircraft aircraft;
	
	@EqualsAndHashCode.Exclude
	private Set<Client> clients;
	
	@EqualsAndHashCode.Exclude
	private Set<Ticket> tickets;

}
