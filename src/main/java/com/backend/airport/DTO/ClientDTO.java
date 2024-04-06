package com.backend.airport.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {
	
	private Long id;
	
	private String passportId;
	
	private String firstname;
	
	private String surname;
	
	private String patronymic;

}
