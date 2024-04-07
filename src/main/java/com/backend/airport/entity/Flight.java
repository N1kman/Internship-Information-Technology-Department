package com.backend.airport.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

	//@EqualsAndHashCode.Exclude
	//private Aircraft aircraft;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE, CascadeType.PERSIST
            })
    @JoinTable(name = "history",
            joinColumns = @JoinColumn(name = "id_flight"),
            inverseJoinColumns = @JoinColumn(name = "id_client"))
    private Set<Client> clients = new HashSet<>();
	
	//@EqualsAndHashCode.Exclude
	//private Set<Ticket> tickets;
    
    public void addClient(Client client) {
    	clients.add(client);
    	client.getFlights().add(this);
    }
    
    public void removeClient(Client client) {
    	clients.remove(client);
    	client.getFlights().remove(this);
    }

}