package com.backend.airport.DTO;

import java.util.Set;

import com.backend.airport.entity.FlightInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightDTO {
	
	private Long id;
	
	private String code;
	
	private FlightInfo flightInfo;
	
	private Set<ClientDTO> clients;

}
