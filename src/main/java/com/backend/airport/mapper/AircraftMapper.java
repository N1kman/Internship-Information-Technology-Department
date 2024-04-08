package com.backend.airport.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.backend.airport.DTO.AircraftDTO;
import com.backend.airport.entity.Aircraft;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AircraftMapper {

	AircraftDTO toDTO(Aircraft aircraft);
}
