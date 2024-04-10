package com.backend.airport.mapper;

import java.util.Set;

import org.mapstruct.Mapper;

import com.backend.airport.dto.FlightDTO;
import com.backend.airport.entity.Flight;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { AircraftMapper.class, TicketMapper.class})
public interface FlightMapper {
	
	FlightDTO toDTO(Flight flight);
	
	Set<FlightDTO> toDTOs(Set<Flight> flights);
	
	Flight toFlight(FlightDTO flight);
	
	Set<Flight> toFlights(Set<FlightDTO> flights);
}
