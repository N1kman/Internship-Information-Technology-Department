package com.crud.app;

import com.crud.app.entities.Aircraft;
import com.crud.app.exceptions.DAOException;
import com.crud.app.services.DAO;
import com.crud.app.services.impl.AircraftDAOImpl;
import com.crud.app.services.impl.ServiceConnectionFactory;

public class App 
{
    public static void main(String[] args)
    {
//    	DAO<Seat> dao = new SeatDAOImpl();
//    	try {
//    		ServiceConnectionFactory.setLocalDriverFromProperties();
//			Seat seat = dao.findById(1);
//			System.out.print(seat);
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
    	
    	DAO<Aircraft> dao = new AircraftDAOImpl();
    	try {
    		ServiceConnectionFactory.setLocalDriverFromProperties();
			
			System.out.println(dao.findById(2));

		} catch (DAOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    }
}
