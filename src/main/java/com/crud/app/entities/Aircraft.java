package com.crud.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Aircraft {
	Long id;
	String registrationNumber;
	String model;
	String company;
}
