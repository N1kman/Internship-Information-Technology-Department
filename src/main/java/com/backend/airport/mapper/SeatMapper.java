package com.backend.airport.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.backend.airport.DTO.SeatDTO;
import com.backend.airport.entity.Seat;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = AircraftMapper.class)
public interface SeatMapper {
	
	SeatDTO toDTO(Seat seat);
	
	Seat toSeat(SeatDTO seat);
}
