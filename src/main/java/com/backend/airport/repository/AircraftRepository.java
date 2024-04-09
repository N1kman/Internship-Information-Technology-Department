package com.backend.airport.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.airport.entity.Aircraft;

@Repository
public interface AircraftRepository extends CrudRepository<Aircraft, Long> {
	
	@Override
	List<Aircraft> findAll();

	Aircraft getById(Long id);
}
