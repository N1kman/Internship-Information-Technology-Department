package com.backend.airport.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FlightInfo {
	
	@Column(name = "place_departure")
	private String placeDeparture;
	
	@Column(name = "place_arrival")
	private String placeArrival;
	
	@Column(name = "date_departure")
	private LocalDateTime dateDeparture;
	
	@Column(name = "date_arrival")
	private LocalDateTime dateArrival;
}
