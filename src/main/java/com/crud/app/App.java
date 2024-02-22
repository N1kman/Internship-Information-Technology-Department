package com.crud.app;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crud.app.entities.Aircraft;
import com.crud.app.services.impl.ServiceDBConnection;

public class App 
{
	public static final Logger logger = LoggerFactory.getLogger(App.class.getName());
    public static void main( String[] args )
    {
    	Aircraft aircraft = new Aircraft(1l, "777", "air", "Belavia");
    	logger.trace("Creates aircraft {}", aircraft);
    	ServiceDBConnection serviceDBConnection = new ServiceDBConnection();
    	
    	try {
    		serviceDBConnection.setLocalDriver();
    		Connection connection = serviceDBConnection.openConnection();
    		serviceDBConnection.closeConnection(connection);
    	} catch(SQLException exception) {
    		logger.error("Problems with SQL connection. Status: {}", exception.getErrorCode());
    	} catch(ClassNotFoundException exception) {
    		logger.error("Problems setting LocalDriver. Message: {}", exception.getMessage());
    	}
    	
    }
}
