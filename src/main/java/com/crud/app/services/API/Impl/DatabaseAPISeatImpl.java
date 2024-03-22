package com.crud.app.services.API.Impl;

import java.sql.SQLException;
import java.util.List;

import com.crud.app.entities.Seat;
import com.crud.app.services.API.DatabaseAPISeat;
import com.crud.app.services.DAO.DAO;
import com.crud.app.services.DAO.Impl.SeatDAOImpl;
import com.crud.app.utils.ServiceConnectionFactory;

public class DatabaseAPISeatImpl implements DatabaseAPISeat {
	
	@Override
	public Seat findById(long id) {
		Seat seat = null;
		try {
			ServiceConnectionFactory.setLocalDriver("org.postgresql.Driver");
		    DAO<Seat> service = new SeatDAOImpl();
			seat = service.findById(id);
			System.out.println(seat);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return seat;
	}

	@Override
	public List<Seat> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seat insert(Seat template) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seat update(Seat template) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seat delete(Seat template) {
		// TODO Auto-generated method stub
		return null;
	}
}
