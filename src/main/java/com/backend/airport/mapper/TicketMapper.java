package com.backend.airport.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.backend.airport.DTO.TicketDTO;
import com.backend.airport.entity.Ticket;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { SeatMapper.class, ReservationMapper.class } )
public interface TicketMapper {
	
	TicketDTO toDTO(Ticket ticket);
	
	Set<TicketDTO> toDTOs(Set<Ticket> tickets);
}
