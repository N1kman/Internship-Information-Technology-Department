package com.backend.airport.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.backend.airport.DTO.ClientDTO;
import com.backend.airport.entity.Client;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { FlightMapper.class, ReservationMapper.class } )
public interface ClientMapper {
	
	ClientDTO toDTO(Client client);
	
	Set<ClientDTO> toDTOs(Set<Client> client);
	
	@Mapping(target = "flights", ignore = true)
	@Mapping(target = "reservations", ignore = true)
	Client toClient(ClientDTO client);
	
	Set<Client> toClients(Set<ClientDTO> client);
}
