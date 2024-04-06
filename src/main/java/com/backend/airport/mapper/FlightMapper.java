package com.backend.airport.mapper;

import org.mapstruct.Mapper;

import com.backend.airport.DTO.FlightDTO;
import com.backend.airport.entity.Flight;

@Mapper(componentModel = "spring",uses = ClientMapper.class)
public interface FlightMapper {
	
	FlightDTO toDTO(Flight flight);
}
