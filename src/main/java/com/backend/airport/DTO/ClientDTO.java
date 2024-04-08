package com.backend.airport.DTO;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"passportId", "firstname", "surname", "patronymic"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {
	
	private Long id;
	
	private String passportId;
	
	private String firstname;
	
	private String surname;
	
	private String patronymic;
	
	private Set<FlightDTO> flights;
	
	private Set<ReservationDTO> reservations;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassportId() {
		return passportId;
	}

	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public Set<FlightDTO> getFlights() {
		return flights;
	}

	public void setFlights(Set<FlightDTO> flights) {
		this.flights = flights;
	}

	public Set<ReservationDTO> getReservations() {
		return reservations;
	}

	public void setReservations(Set<ReservationDTO> reservations) {
		this.reservations = reservations;
	}

}
