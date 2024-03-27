package com.crud.app.services.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crud.app.entities.Flight;
import com.crud.app.exceptions.Messages;
import com.crud.app.entities.Client;
import com.crud.app.services.dao.ClientDAO;
import com.crud.app.utils.ServiceConnectionFactory;

public class ClientDAOImpl implements ClientDAO {

	ServiceConnectionFactory serviceConnectionFactory = new ServiceConnectionFactory();

	public static final Logger logger = LoggerFactory.getLogger(ClientDAOImpl.class);

	public static final String SQL_REQUEST_FIND_ALL = "SELECT client.id, client.passport_id, client.firstname, client.surname, client.patronymic, flight.id AS id_flight, flight.code, flight.place_departure, flight.place_arrival, flight.date_departure, flight.date_arrival FROM client LEFT JOIN history ON client.id=history.id_client LEFT JOIN flight ON flight.id=history.id_flight";
	public static final String SQL_REQUEST_FIND_BY_ID = "SELECT client.id, client.passport_id, client.firstname, client.surname, client.patronymic, flight.id AS id_flight, flight.code, flight.place_departure, flight.place_arrival, flight.date_departure, flight.date_arrival FROM client LEFT JOIN history ON client.id=history.id_client LEFT JOIN flight ON flight.id=history.id_flight WHERE client.id=?";
	public static final String SQL_REQUEST_INSERT = "INSERT INTO client (passport_id, firstname, surname, patronymic) VALUES (?, ?, ?, ?)";
	public static final String SQL_REQUEST_UPDATE = "UPDATE client SET passport_id=?, firstname=?, surname=?, patronymic=? WHERE id=?";
	public static final String SQL_REQUEST_DELETE = "DELETE FROM client WHERE id=?";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_PASSPORT_ID = "passport_id";
	public static final String COLUMN_FISRTNAME = "firstname";
	public static final String COLUMN_SURNAME = "surname";
	public static final String COLUMN_PATRONYMIC = "patronymic";

	public static final String COLUMN_ID_FLIGHT = "id_flight";
	public static final String COLUMN_CODE = "code";
	public static final String COLUMN_PLACE_DEPARTURE = "place_departure";
	public static final String COLUMN_PLACE_ARRIVAL = "place_arrival";
	public static final String COLUMN_DATE_DEPARTURE = "date_departure";
	public static final String COLUMN_DATE_ARRIVAL = "date_arrival";

	@Override
	public Client findById(long id) throws SQLException {
		Connection conn = null;
		Client client = null;
		Flight flight = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();

			logger.trace(Messages.PROCESS_CREATE_STATEMENT);
			st = conn.prepareStatement(SQL_REQUEST_FIND_BY_ID);
			st.setLong(1, id);

			logger.trace(Messages.PROCESS_GET_RESULT_SET);
			rs = st.executeQuery();

			while (rs.next()) {
				if (client == null) {
					client = new Client(rs.getLong(COLUMN_ID), rs.getString(COLUMN_PASSPORT_ID),
							rs.getString(COLUMN_FISRTNAME), rs.getString(COLUMN_SURNAME),
							rs.getString(COLUMN_PATRONYMIC), new HashSet<>());
				}
				if (rs.getLong(COLUMN_ID_FLIGHT) != 0) {
					flight = new Flight(rs.getLong(COLUMN_ID_FLIGHT), rs.getString(COLUMN_CODE),
							rs.getString(COLUMN_PLACE_DEPARTURE), rs.getString(COLUMN_PLACE_ARRIVAL),
							rs.getTimestamp(COLUMN_DATE_DEPARTURE).toLocalDateTime(),
							rs.getTimestamp(COLUMN_DATE_ARRIVAL).toLocalDateTime(), null);
					client.getFlights().add(flight);
				}
			}

			logger.trace(Messages.SUCCESS_EXECUTED);

		} finally {
			try {
				rs.close();
				logger.trace(Messages.SUCCESS_RESULT_SET_CLOSED);

				st.close();
				logger.trace(Messages.SUCCESS_RESULT_SET_CLOSED);

				serviceConnectionFactory.closeConnection(conn);
				logger.trace(Messages.SUCCESS_CONNECTION_CLOSED);

			} catch (SQLException e) {
				logger.error(Messages.UNSUCCESS_CLOSED, e);
			}
		}

		return client;
	}

	@Override
	public List<Client> findAll() throws SQLException {
		Connection conn = null;
		Client client = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Client> list = new LinkedList<>();

		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();

			logger.trace(Messages.PROCESS_CREATE_STATEMENT);
			st = conn.prepareStatement(SQL_REQUEST_FIND_ALL);

			logger.trace(Messages.PROCESS_GET_RESULT_SET);
			rs = st.executeQuery();

			while (rs.next()) {
				client = new Client(rs.getLong(COLUMN_ID), rs.getString(COLUMN_PASSPORT_ID),
						rs.getString(COLUMN_FISRTNAME), rs.getString(COLUMN_SURNAME), rs.getString(COLUMN_PATRONYMIC),
						new HashSet<>());
				if (!list.contains(client)) {
					list.add(client);
				}
				Long id = rs.getLong(COLUMN_ID);
				if (rs.getLong(COLUMN_ID_FLIGHT) != 0) {
					Flight flight = new Flight(rs.getLong(COLUMN_ID_FLIGHT), rs.getString(COLUMN_CODE),
							rs.getString(COLUMN_PLACE_DEPARTURE), rs.getString(COLUMN_PLACE_ARRIVAL),
							rs.getTimestamp(COLUMN_DATE_DEPARTURE).toLocalDateTime(),
							rs.getTimestamp(COLUMN_DATE_ARRIVAL).toLocalDateTime(), null);

					list.stream().filter(buffer -> buffer.getId() == id)
							.forEach(buffer -> buffer.getFlights().add(flight));
				}
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

		return list;
	}

	@Override
	public Client insert(Client template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Client client = null;

		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();

			logger.trace(Messages.PROCESS_CREATE_STATEMENT);
			st = conn.prepareStatement(SQL_REQUEST_INSERT, Statement.RETURN_GENERATED_KEYS);
			setFields(st, template);
			st.execute();

			logger.trace(Messages.PROCESS_GET_RESULT_SET);
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				client = new Client(rs.getLong(COLUMN_ID), rs.getString(COLUMN_PASSPORT_ID),
						rs.getString(COLUMN_FISRTNAME), rs.getString(COLUMN_SURNAME), rs.getString(COLUMN_PATRONYMIC),
						null);
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

		return client;
	}

	@Override
	public Client update(Client template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Client client = null;

		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();

			logger.trace(Messages.PROCESS_CREATE_STATEMENT);
			st = conn.prepareStatement(SQL_REQUEST_UPDATE, Statement.RETURN_GENERATED_KEYS);
			setFields(st, template);
			st.setLong(5, template.getId());
			st.execute();

			logger.trace(Messages.PROCESS_GET_RESULT_SET);
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				client = new Client(rs.getLong(COLUMN_ID), rs.getString(COLUMN_PASSPORT_ID),
						rs.getString(COLUMN_FISRTNAME), rs.getString(COLUMN_SURNAME), rs.getString(COLUMN_PATRONYMIC),
						null);
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

		return client;
	}

	@Override
	public Client delete(Client template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Client client = null;

		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();

			logger.trace(Messages.PROCESS_CREATE_STATEMENT);
			st = conn.prepareStatement(SQL_REQUEST_DELETE, Statement.RETURN_GENERATED_KEYS);
			st.setLong(1, template.getId());
			st.execute();

			logger.trace(Messages.PROCESS_GET_RESULT_SET);
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				client = new Client(rs.getLong(COLUMN_ID), rs.getString(COLUMN_PASSPORT_ID),
						rs.getString(COLUMN_FISRTNAME), rs.getString(COLUMN_SURNAME), rs.getString(COLUMN_PATRONYMIC),
						null);
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

		return client;
	}

	private void setFields(PreparedStatement st, Client template) throws SQLException {
		st.setString(1, template.getPassportId());
		st.setString(2, template.getFirstname());
		st.setString(3, template.getSurname());
		st.setString(4, template.getPatronymic());
	}

}
