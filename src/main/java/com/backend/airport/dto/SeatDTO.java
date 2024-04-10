package com.backend.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"seatNumber", "seatType"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatDTO {

	private Long id;

	private String seatNumber;

	private String seatType;
	
	private AircraftDTO aircraft;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public AircraftDTO getAircraft() {
		return aircraft;
	}

	public void setAircraft(AircraftDTO aircraft) {
		this.aircraft = aircraft;
	}
}
