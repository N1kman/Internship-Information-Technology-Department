package com.crud.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aircraft {
	Long id;
	String registrationNumber;
	String model;
	String company;
}
