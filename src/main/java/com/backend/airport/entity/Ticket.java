package com.backend.airport.entity;

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
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Exclude
	private Long id;
	
	private String ticketNumber;
	
	private Boolean ticketStatus;
	
	@EqualsAndHashCode.Exclude
	private Flight flight;
	
	@EqualsAndHashCode.Exclude
	private Seat seat;
	
	@EqualsAndHashCode.Exclude
	private Reservation reservation;
}
