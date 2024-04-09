package com.backend.airport.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.airport.entity.Seat;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Long> {
	
	@Override
	List<Seat> findAll();

	Seat getById(Long id);
}
