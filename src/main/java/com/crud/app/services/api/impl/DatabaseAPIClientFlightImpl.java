package com.crud.app.services.api.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crud.app.entities.ClientFlight;
import com.crud.app.exceptions.Messages;
import com.crud.app.services.api.DatabaseAPIClientFlight;
import com.crud.app.services.dao.DAO;
import com.crud.app.services.dao.impl.ClientFlightDAOImpl;

public class DatabaseAPIClientFlightImpl implements DatabaseAPIClientFlight {
	
	public static final Logger logger = LoggerFactory.getLogger(DatabaseAPIClientFlightImpl.class);

	@Override
	public ClientFlight findById(long id) {
		return null;
	}

	@Override
	public List<ClientFlight> findAll() {
		List<ClientFlight> clientFlights = null;
		try {
		    DAO<ClientFlight> service = new ClientFlightDAOImpl();
		    clientFlights = service.findAll();
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
		}
    	return clientFlights;
	}

	@Override
	public ClientFlight insert(ClientFlight template) {
		ClientFlight сlientFlight = null;
		try {
		    DAO<ClientFlight> service = new ClientFlightDAOImpl();
		    сlientFlight = service.insert(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
		}
    	return сlientFlight;
	}

	@Override
	public ClientFlight update(ClientFlight template) {
		return null;
	}

	@Override
	public ClientFlight delete(ClientFlight template) {
		return null;
	}

}
