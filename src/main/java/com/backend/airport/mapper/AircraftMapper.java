package com.backend.airport.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.backend.airport.DTO.AircraftDTO;
import com.backend.airport.entity.Aircraft;
import org.mapstruct.Mapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AircraftMapper {
	
	AircraftDTO toDTO(Aircraft aircraft);
	
	@Mapping(target = "flights", ignore = true)
	@Mapping(target = "seats", ignore = true)
	Aircraft toAircraft(AircraftDTO aircraft);
}
