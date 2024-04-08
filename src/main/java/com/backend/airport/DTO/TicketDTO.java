package com.backend.airport.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"ticketNumber", "ticketStatus"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDTO {
	
	private Long id;
	
	private String ticketNumber;

	private Boolean ticketStatus;

	private SeatDTO seat;
	
	private ReservationDTO reservation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public Boolean getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(Boolean ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public SeatDTO getSeat() {
		return seat;
	}

	public void setSeat(SeatDTO seat) {
		this.seat = seat;
	}

	public ReservationDTO getReservation() {
		return reservation;
	}

	public void setReservation(ReservationDTO reservation) {
		this.reservation = reservation;
	}

}
