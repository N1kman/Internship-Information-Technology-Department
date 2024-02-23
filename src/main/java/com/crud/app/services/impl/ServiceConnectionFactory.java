package com.crud.app.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.crud.app.services.ConnectionFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceConnectionFactory implements ConnectionFactory {
	
	public static String PROPERTY_DRIVER = "datasource.driver";
	
	private static String PROPERTY_URL = "datasource.url";
	
	private static String PROPERTY_USERNAME = "datasource.username";
	
	private static String PROPERTY_PASSWORD = "datasource.password";
	
	private static String PROPERTY_FILE_PATH = "application.properties";
	
	public static void setLocalDriver(String driverPath) throws ClassNotFoundException {
		Class.forName(driverPath);
	}
	
	public static void setLocalDriverFromProperties() throws ClassNotFoundException {
		PropertiesConfiguration props = ServiceProps.loadProps(PROPERTY_FILE_PATH);
		if(props != null) {
			Class.forName(props.getString(PROPERTY_DRIVER));
		}
	}
	
	@Override
	public Connection openConnection() throws SQLException {
		PropertiesConfiguration props = ServiceProps.loadProps(PROPERTY_FILE_PATH);
		return DriverManager.getConnection( props.getString(PROPERTY_URL),
											props.getString(PROPERTY_USERNAME),
											props.getString(PROPERTY_PASSWORD));
	}

	@Override
	public void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}
}
