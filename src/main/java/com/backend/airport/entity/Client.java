package com.backend.airport.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
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
	
    @ManyToMany(mappedBy = "clients")
	private Set<Flight> flights = new HashSet<>();
	
	//@EqualsAndHashCode.Exclude
	//private Set<Reservation> reservation;
}
