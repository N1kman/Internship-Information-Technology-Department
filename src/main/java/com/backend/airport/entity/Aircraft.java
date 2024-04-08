package com.backend.airport.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"registrationNumber", "model", "company"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aircraft")
@Entity
public class Aircraft {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "registration_number")
	private String registrationNumber;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "company")
	private String company;

	//private Set<Seat> seats = null;
	
	@OneToMany(mappedBy="aircraft")
	private Set<Flight> flights = new HashSet<>();
	

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

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}
}
