package com.backend.airport.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"passportId", "firstname", "surname", "patronymic"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "passport_id")
	private String passportId;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "surname")
	private String surname;

	@Column(name = "patronymic")
	private String patronymic;

	@ManyToMany(mappedBy = "clients", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Set<Flight> flights = new HashSet<>();

	@OneToMany(mappedBy="client", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, orphanRemoval = true)
	private Set<Reservation> reservations = new HashSet<>();

	public void addFlight(Flight flight) {
		flights.add(flight);
		flight.getClients().add(this);
	}

	public void removeFlight(Flight flight) {
		flights.remove(flight);
		flight.getClients().remove(this);
	}	
	
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
		reservation.setClient(this);
	}

	public void removeReservation(Reservation reservation) {
		reservations.remove(reservation);
		reservation.setClient(null);
	}

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

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

}
