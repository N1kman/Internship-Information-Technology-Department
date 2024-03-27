package com.crud.app.services.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crud.app.entities.ClientFlight;
import com.crud.app.exceptions.Messages;
import com.crud.app.services.dao.ClientFlightDAO;
import com.crud.app.utils.ServiceConnectionFactory;

public class ClientFlightDAOImpl implements ClientFlightDAO {
	
	ServiceConnectionFactory serviceConnectionFactory = new ServiceConnectionFactory();

	public static final Logger logger = LoggerFactory.getLogger(ClientFlightDAOImpl.class);
	
	public static final String SQL_REQUEST_INSERT = "INSERT INTO history(id_flight, id_client) VALUES (?, ?)";
	public static final String SQL_REQUEST_FIND_ALL = "SELECT id_flight, id_client FROM history";
	
	public static final String COLUMN_ID_FLIGHT = "id_flight";
	public static final String COLUMN_ID_CLIENT = "id_client";
	
	@Override
	public ClientFlight findById(long id) throws SQLException {
		return null;
	}

	@Override
	public List<ClientFlight> findAll() throws SQLException {
		Connection conn = null;
		ClientFlight clientFlight = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<ClientFlight> list = new LinkedList<>();

		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();

			logger.trace(Messages.PROCESS_CREATE_STATEMENT);
			st = conn.prepareStatement(SQL_REQUEST_FIND_ALL);

			logger.trace(Messages.PROCESS_GET_RESULT_SET);
			rs = st.executeQuery();
			while (rs.next()) {
				clientFlight = new ClientFlight(rs.getLong(COLUMN_ID_CLIENT), rs.getLong(COLUMN_ID_FLIGHT));
				list.add(clientFlight);
			}
			
			logger.info(list.toString());

			logger.trace(Messages.SUCCESS_EXECUTED);

		} finally {
			try {
				rs.close();
				logger.trace(Messages.SUCCESS_RESULT_SET_CLOSED);

				st.close();
				logger.trace(Messages.SUCCESS_STATEMENT_CLOSED);

				serviceConnectionFactory.closeConnection(conn);
				logger.trace(Messages.SUCCESS_CONNECTION_CLOSED);

			} catch (SQLException e) {
				logger.error(Messages.UNSUCCESS_CLOSED, e);
			}
		}

		return list;
	}

	@Override
	public ClientFlight insert(ClientFlight template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ClientFlight clientFlight = null;

		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();

			logger.trace(Messages.PROCESS_CREATE_STATEMENT);
			st = conn.prepareStatement(SQL_REQUEST_INSERT, Statement.RETURN_GENERATED_KEYS);
			st.setLong(1, template.getFlight().getId());
			st.setLong(2, template.getClient().getId());
			st.execute();

			logger.trace(Messages.PROCESS_GET_RESULT_SET);
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				clientFlight = new ClientFlight(rs.getLong(COLUMN_ID_CLIENT), rs.getLong(COLUMN_ID_FLIGHT));
			}

			logger.trace(Messages.SUCCESS_EXECUTED);

		} finally {
			try {
				rs.close();
				logger.trace(Messages.SUCCESS_RESULT_SET_CLOSED);

				st.close();
				logger.trace(Messages.SUCCESS_STATEMENT_CLOSED);

				serviceConnectionFactory.closeConnection(conn);
				logger.trace(Messages.SUCCESS_CONNECTION_CLOSED);

			} catch (SQLException e) {
				logger.error(Messages.UNSUCCESS_CLOSED, e);
			}
		}

		return clientFlight;
	}

	@Override
	public ClientFlight update(ClientFlight template) throws SQLException {
		return null;
	}

	@Override
	public ClientFlight delete(ClientFlight template) throws SQLException {
		return null;
	}

}
