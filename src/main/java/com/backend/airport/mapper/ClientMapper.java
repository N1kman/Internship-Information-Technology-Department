package com.backend.airport.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.backend.airport.DTO.ClientDTO;
import com.backend.airport.entity.Client;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { FlightMapper.class, ReservationMapper.class } )
public interface ClientMapper {
	
	ClientDTO toDTO(Client client);
}
