package com.crud.app.services;

import java.sql.SQLException;

import com.crud.app.entities.Seat;
import com.crud.app.services.impl.SeatDAOImpl;
import com.crud.app.services.impl.ServiceConnectionFactory;

public class SeatMenu {
	public static String test() {
		return "LOL";
	}
	
	public static String findById(long id) {
		Seat seat = null;
		try {
			//ServiceConnectionFactory.setLocalDriver("org.postgresql.Driver");
		    DAO<Seat> service = new SeatDAOImpl();
			seat = service.findById(2);
			System.out.println(seat);
		//} catch (ClassNotFoundException e) {
		//	e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    	return seat.toString();
	}
}
