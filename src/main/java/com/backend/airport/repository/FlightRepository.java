package com.backend.airport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.airport.entity.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
	//@Query("SELECT f FROM Flight f JOIN FETCH f.clients WHERE f.id = :id")
	//Flight findFlightWithClients(@Param("id") Long id);
	
}
