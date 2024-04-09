package com.backend.airport.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.airport.entity.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	
	@Override
	List<Reservation> findAll();

	Reservation getById(Long id);
}
