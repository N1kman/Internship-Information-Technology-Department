package com.backend.airport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.airport.entity.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
	@EntityGraph(attributePaths = {"clients"})
	Optional<Flight> findById(Long id);
}
