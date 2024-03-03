package com.crud.app.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.crud.app.services.ConnectionFactory;
import com.crud.app.utils.ServicePropsUtils;


public class ServiceConnectionFactory implements ConnectionFactory {
	
	private static final String PROPERTY_DRIVER = "datasource.driver";
	
	private static final String PROPERTY_URL = "datasource.url";
	
	private static final String PROPERTY_USERNAME = "datasource.username";
	
	private static final String PROPERTY_PASSWORD = "datasource.password";
	
	private static final String PROPERTY_FILE_PATH = "database.properties";
	
	public static void setLocalDriver(String driverPath) throws ClassNotFoundException {
		Class.forName(driverPath);
	}
	
	public static void setLocalDriverFromProperties() throws ClassNotFoundException {
		PropertiesConfiguration props = ServicePropsUtils.loadProps(PROPERTY_FILE_PATH);
		if(props != null) {
			Class.forName(props.getString(PROPERTY_DRIVER));
		}
	}
	
	@Override
	public Connection openConnection() throws SQLException {
		PropertiesConfiguration props = ServicePropsUtils.loadProps(PROPERTY_FILE_PATH);
		return DriverManager.getConnection( props.getString(PROPERTY_URL),
											props.getString(PROPERTY_USERNAME),
											props.getString(PROPERTY_PASSWORD));
	}

	@Override
	public void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}
}
