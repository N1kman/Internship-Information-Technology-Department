package com.backend.airport.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"placeDeparture", "placeArrival", "dateDeparture", "dateArrival"})
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
