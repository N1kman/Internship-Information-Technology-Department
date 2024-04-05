package com.backend.airport.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FlightInfo {
	
	private String placeDeparture;
	
	private String placeArrival;
	
	private LocalDateTime dateDeparture;
	
	private LocalDateTime dateArrival;
}
