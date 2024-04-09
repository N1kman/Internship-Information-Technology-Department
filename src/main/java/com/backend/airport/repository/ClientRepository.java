package com.backend.airport.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.airport.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
	
	@Override
	List<Client> findAll();

	Client getById(Long id);
}
