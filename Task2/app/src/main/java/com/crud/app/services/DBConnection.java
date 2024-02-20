package com.crud.app.services;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection {
	public Connection openConnection() throws SQLException;
	public void closeConnection(Connection connection) throws SQLException;
}
