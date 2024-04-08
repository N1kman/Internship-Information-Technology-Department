package com.backend.airport.mapper;

import org.mapstruct.Mapper;

import com.backend.airport.DTO.FlightDTO;
import com.backend.airport.entity.Flight;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ClientMapper.class, AircraftMapper.class})
public interface FlightMapper {
	
	FlightDTO toDTO(Flight flight);
}
