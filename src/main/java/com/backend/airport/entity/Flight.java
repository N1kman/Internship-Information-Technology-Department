package com.backend.airport.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"code", "flightInfo"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flight")
@Entity
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;

	@Embedded
	private FlightInfo flightInfo = new FlightInfo();
	
	@ManyToOne
    @JoinColumn(name="id_aircraft", nullable=false)
	private Aircraft aircraft = new Aircraft();

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(
			name = "history", 
			joinColumns = { @JoinColumn(name = "id_flight") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_client") }
	)
	private Set<Client> clients = new HashSet<>();

	// @EqualsAndHashCode.Exclude
	// private Set<Ticket> tickets;

	public void addClient(Client client) {
		clients.add(client);
		client.getFlights().add(this);
	}

	public void removeClient(Client client) {
		clients.remove(client);
		client.getFlights().remove(this);
	}
	

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

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

}