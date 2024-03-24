package com.crud.app.services.api.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crud.app.entities.Flight;
import com.crud.app.exceptions.Messages;
import com.crud.app.services.api.DatabaseAPIFlight;
import com.crud.app.services.dao.DAO;
import com.crud.app.services.dao.impl.FlightDAOImpl;

public class DatabaseAPIFlightImpl implements DatabaseAPIFlight {

	public static final Logger logger = LoggerFactory.getLogger(DatabaseAPIFlightImpl.class);
	
	@Override
	public Flight findById(long id) {
		Flight flight = null;
		try {
		    DAO<Flight> service = new FlightDAOImpl();
		    flight = service.findById(id);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED);
			e.printStackTrace();
		}
    	return flight;
	}

	@Override
	public List<Flight> findAll() {
		List<Flight> flights = null;
		try {
		    DAO<Flight> service = new FlightDAOImpl();
		    flights = service.findAll();
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED);
			e.printStackTrace();
		}
    	return flights;
	}

	@Override
	public Flight insert(Flight template) {
		Flight flight = null;
		try {
		    DAO<Flight> service = new FlightDAOImpl();
		    flight = service.insert(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED);
			e.printStackTrace();
		}
    	return flight;
	}

	@Override
	public Flight update(Flight template) {
		Flight flight = null;
		try {
		    DAO<Flight> service = new FlightDAOImpl();
		    flight = service.update(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED);
			e.printStackTrace();
		}
    	return flight;
	}

	@Override
	public Flight delete(Flight template) {
		Flight flight = null;
		try {
		    DAO<Flight> service = new FlightDAOImpl();
		    flight = service.delete(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED);
			e.printStackTrace();
		}
    	return flight;
	}

}
