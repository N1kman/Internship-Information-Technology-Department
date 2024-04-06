package com.backend.airport.mapper;

import java.util.Set;

import org.mapstruct.Mapper;

import com.backend.airport.DTO.ClientDTO;
import com.backend.airport.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	
	ClientDTO toDTO(Client сlient);
	
	Set<ClientDTO> toDTOs(Set<Client> сlients);
}
