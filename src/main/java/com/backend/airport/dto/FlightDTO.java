package com.backend.airport.dto;

import java.util.Set;

import com.backend.airport.entity.FlightInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"code", "flightInfo"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightDTO {
	
	private Long id;
	
	private String code;
	
	private FlightInfo flightInfo;
	
	private AircraftDTO aircraft;
	
	private Set<TicketDTO> tickets;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public FlightInfo getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(FlightInfo flightInfo) {
		this.flightInfo = flightInfo;
	}

	public AircraftDTO getAircraft() {
		return aircraft;
	}

	public void setAircraft(AircraftDTO aircraft) {
		this.aircraft = aircraft;
	}

	public Set<TicketDTO> getTickets() {
		return tickets;
	}

	public void setTickets(Set<TicketDTO> tickets) {
		this.tickets = tickets;
	}

}
