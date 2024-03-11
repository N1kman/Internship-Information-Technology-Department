package com.crud.app;

import com.crud.app.entities.Flight;
import com.crud.app.exceptions.DAOException;
import com.crud.app.services.DAO;
import com.crud.app.services.impl.FlightDAOImpl;
import com.crud.app.services.impl.ServiceConnectionFactory;

public class App 
{
    public static void main(String[] args)
    {
    	
    	DAO<Flight> dao = new FlightDAOImpl();
    	try {
    		ServiceConnectionFactory.setLocalDriverFromProperties();	
			System.out.println(dao.findAll());

		} catch (DAOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    }
}
