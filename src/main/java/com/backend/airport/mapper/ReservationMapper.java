package com.backend.airport.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.backend.airport.dto.ReservationDTO;
import com.backend.airport.entity.Reservation;
import org.mapstruct.Mapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationMapper {
	
	ReservationDTO toDTO(Reservation reservation);
	
	Set<ReservationDTO> toDTOs(Set<Reservation> reservations);
	
	@Mapping(target = "ticket", ignore = true)
	Reservation toReservation(ReservationDTO reservation);
	
	Set<Reservation> toReservations(Set<ReservationDTO> reservations);
}
