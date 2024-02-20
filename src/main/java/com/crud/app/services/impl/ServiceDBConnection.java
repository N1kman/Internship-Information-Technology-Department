package com.crud.app.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.crud.app.services.DBConnection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDBConnection implements DBConnection {
	private String url;
	private String username;
	private String password;
	
	public ServiceDBConnection() {
		PropertiesConfiguration props = new ServiceProps("application.properties").loadProps();
		if(props != null) {
			url = props.getString("datasource.url");
			username = props.getString("datasource.username");
			password = props.getString("datasource.password");
		} else {
			url = "";
			username = "";
			password = "";
		}
	}
	
	public void setLocalDriver(String driverPath) throws ClassNotFoundException {
		Class.forName(driverPath);
	}
	
	public void setLocalDriver() throws ClassNotFoundException {
		PropertiesConfiguration props = new ServiceProps("application.properties").loadProps();
		if(props != null) {
			Class.forName(props.getString("datasource.driver"));
		}
	}
	
	@Override
	public Connection openConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}
}
