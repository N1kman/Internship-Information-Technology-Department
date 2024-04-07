package com.backend.airport.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.backend.airport.DTO.ClientDTO;
import com.backend.airport.entity.Client;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
	
	ClientDTO toDTO(Client client);
	
	Set<ClientDTO> toDTOs(Set<Client> clients);
}
