package com.backend.airport.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"reservationDate"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservation")
@Entity
public class Reservation {

	@Id
	@Column(name = "id_ticket")
	private Long id;
	
	@Column(name = "reservation_date")
	private LocalDateTime reservationDate;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "id_ticket")
	private Ticket ticket;
	
	@ManyToOne
    @JoinColumn(name="id_client", nullable=false)
	private Client client;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
