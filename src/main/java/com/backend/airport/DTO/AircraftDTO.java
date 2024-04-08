package com.backend.airport.DTO;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"registrationNumber", "model", "company"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AircraftDTO {
	
	private Long id;
	
	private String registrationNumber;
	
	private String model;

	private String company;
	
	private Set<SeatDTO> seats;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Set<SeatDTO> getSeats() {
		return seats;
	}

	public void setSeats(Set<SeatDTO> seats) {
		this.seats = seats;
	}

}
