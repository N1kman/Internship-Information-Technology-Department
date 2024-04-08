package com.backend.airport.DTO;

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
	
	private AircraftDTO aircratf;
	
	private Set<ClientDTO> clients;

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

	public AircraftDTO getAircratf() {
		return aircratf;
	}

	public void setAircratf(AircraftDTO aircratf) {
		this.aircratf = aircratf;
	}

	public Set<ClientDTO> getClients() {
		return clients;
	}

	public void setClients(Set<ClientDTO> clients) {
		this.clients = clients;
	}

}
