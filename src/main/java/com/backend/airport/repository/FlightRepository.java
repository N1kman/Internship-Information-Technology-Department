package com.backend.airport.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.airport.entity.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
	
	@Override
	List<Flight> findAll();

	Flight getById(Long id);
}
