package com.crud.app.services.api.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crud.app.entities.Aircraft;
import com.crud.app.exceptions.Messages;
import com.crud.app.services.api.DatabaseAPIAircraft;
import com.crud.app.services.dao.DAO;
import com.crud.app.services.dao.impl.AircraftDAOImpl;

public class DatabaseAPIAircraftImpl implements DatabaseAPIAircraft {
	
	public static final Logger logger = LoggerFactory.getLogger(DatabaseAPIAircraftImpl.class);

	@Override
	public Aircraft findById(long id) {
		Aircraft aircraft = null;
		try {
		    DAO<Aircraft> service = new AircraftDAOImpl();
		    aircraft = service.findById(id);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
		}
    	return aircraft;
	}

	@Override
	public List<Aircraft> findAll() {
		List<Aircraft> aircrafts = null;
		try {
		    DAO<Aircraft> service = new AircraftDAOImpl();
		    aircrafts = service.findAll();
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
		}
    	return aircrafts;
	}

	@Override
	public Aircraft insert(Aircraft template) {
		Aircraft aircraft = null;
		try {
		    DAO<Aircraft> service = new AircraftDAOImpl();
		    aircraft = service.insert(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
		}
    	return aircraft;
	}

	@Override
	public Aircraft update(Aircraft template) {
		Aircraft aircraft = null;
		try {
		    DAO<Aircraft> service = new AircraftDAOImpl();
		    aircraft = service.update(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
		}
    	return aircraft;
	}

	@Override
	public Aircraft delete(Aircraft template) {
		Aircraft aircraft = null;
		try {
		    DAO<Aircraft> service = new AircraftDAOImpl();
		    aircraft = service.delete(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
		}
    	return aircraft;
	}

}
