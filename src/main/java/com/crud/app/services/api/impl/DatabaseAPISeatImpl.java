package com.crud.app.services.api.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crud.app.entities.Seat;
import com.crud.app.services.api.DatabaseAPISeat;
import com.crud.app.services.dao.DAO;
import com.crud.app.services.dao.impl.SeatDAOImpl;
import com.crud.app.exceptions.Messages;

public class DatabaseAPISeatImpl implements DatabaseAPISeat {
	
	public static final Logger logger = LoggerFactory.getLogger(DatabaseAPISeatImpl.class);
	
	@Override
	public Seat findById(long id) {
		Seat seat = null;
		try {
		    DAO<Seat> service = new SeatDAOImpl();
			seat = service.findById(id);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED);
			e.printStackTrace();
		}
    	return seat;
	}

	@Override
	public List<Seat> findAll() {
		List<Seat> seats = null;
		try {
		    DAO<Seat> service = new SeatDAOImpl();
			seats = service.findAll();
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED);
			e.printStackTrace();
		}
    	return seats;
	}

	@Override
	public Seat insert(Seat template) {
		Seat seat = null;
		try {
		    DAO<Seat> service = new SeatDAOImpl();
			seat = service.insert(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED);
			e.printStackTrace();
		}
    	return seat;
	}

	@Override
	public Seat update(Seat template) {
		Seat seat = null;
		try {
		    DAO<Seat> service = new SeatDAOImpl();
			seat = service.update(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED);
			e.printStackTrace();
		}
    	return seat;
	}

	@Override
	public Seat delete(Seat template) {
		Seat seat = null;
		try {
		    DAO<Seat> service = new SeatDAOImpl();
			seat = service.delete(template);
		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED);
			e.printStackTrace();
		}
    	return seat;
	}
}
