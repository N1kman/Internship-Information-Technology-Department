package com.backend.airport.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.airport.entity.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>  {
	
	@Override
	List<Ticket> findAll();

	Ticket getById(Long id);
}
