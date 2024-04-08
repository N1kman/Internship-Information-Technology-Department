package com.backend.airport.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.backend.airport.DTO.SeatDTO;
import com.backend.airport.entity.Seat;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SeatMapper {
	
	SeatDTO toDTO(Seat seat);

	Set<SeatDTO> toDTOs(Set<Seat> seats);
}
