package com.crud.app.services.impl;

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

import com.crud.app.entities.Aircraft;
import com.crud.app.entities.Seat;
import com.crud.app.exceptions.Messages;
import com.crud.app.services.AircraftDAO;

public class AircraftDAOImpl implements AircraftDAO {

	ServiceConnectionFactory serviceConnectionFactory = new ServiceConnectionFactory();

	public static final Logger logger = LoggerFactory.getLogger(AircraftDAOImpl.class);

	public static final String SQL_REQUEST_FIND_ALL = "SELECT aircraft.id, aircraft.registration_number, aircraft.model, aircraft.company, seat.id AS id_seat, seat.seat_number, seat.seat_type FROM aircraft LEFT JOIN seat ON aircraft.id=seat.id_aircraft";
	public static final String SQL_REQUEST_FIND_BY_ID = "SELECT aircraft.id, aircraft.registration_number, aircraft.model, aircraft.company, seat.id AS id_seat, seat.seat_number, seat.seat_type FROM aircraft LEFT JOIN seat ON aircraft.id=seat.id_aircraft WHERE aircraft.id=?";
	public static final String SQL_REQUEST_INSERT = "INSERT INTO aircraft (registration_number, model, company) VALUES (?, ?, ?)";
	public static final String SQL_REQUEST_UPDATE = "UPDATE aircraft SET registration_number=?, model=?, company=? WHERE id=?";
	public static final String SQL_REQUEST_DELETE = "DELETE FROM aircraft WHERE id=?";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_REGISTRATION_NUMBER = "registration_number";
	public static final String COLUMN_MODEL = "model";
	public static final String COLUMN_COMPANY = "company";

	public static final String COLUMN_ID_SEAT = "id_seat";
	public static final String COLUMN_SEAT_NUMBER = "seat_number";
	public static final String COLUMN_SEAT_TYPE = "seat_type";

	@Override
	public Aircraft findById(long id) throws SQLException {
		Connection conn = null;
		Aircraft aircraft = null;
		Seat seat = null;
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
				if (aircraft == null) {
					aircraft = new Aircraft(rs.getLong(COLUMN_ID), rs.getString(COLUMN_REGISTRATION_NUMBER),
							rs.getString(COLUMN_MODEL), rs.getString(COLUMN_COMPANY), new HashSet<>());
				}
				if (rs.getLong(COLUMN_ID_SEAT) != 0) {
					seat = new Seat(rs.getLong(COLUMN_ID_SEAT), rs.getString(COLUMN_SEAT_NUMBER),
							rs.getString(COLUMN_SEAT_TYPE), null);
					aircraft.getSeats().add(seat);
				}
			}

			logger.trace(Messages.SUCCESS_EXECUTED);

		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
			throw e;

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
				throw e;
			}
		}

		return aircraft;
	}

	@Override
	public List<Aircraft> findAll() throws SQLException {
		Connection conn = null;
		Aircraft aircraft = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Aircraft> list = new LinkedList<>();

		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();

			logger.trace(Messages.PROCESS_CREATE_STATEMENT);
			st = conn.prepareStatement(SQL_REQUEST_FIND_ALL);

			logger.trace(Messages.PROCESS_GET_RESULT_SET);
			rs = st.executeQuery();

			while (rs.next()) {
				aircraft = new Aircraft(rs.getLong(COLUMN_ID), rs.getString(COLUMN_REGISTRATION_NUMBER),
						rs.getString(COLUMN_MODEL), rs.getString(COLUMN_COMPANY), new HashSet<>());
				if (!list.contains(aircraft)) {
					list.add(aircraft);
				}
				Long id = rs.getLong(COLUMN_ID);
				Seat seat = new Seat(rs.getLong(COLUMN_ID_SEAT), rs.getString(COLUMN_SEAT_NUMBER),
						rs.getString(COLUMN_SEAT_TYPE), null);
				if (seat.getId() != 0) {
					list.stream().filter(buffer -> buffer.getId() == id).forEach(buffer -> buffer.getSeats().add(seat));
				}
			}

			logger.trace(Messages.SUCCESS_EXECUTED);

		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
			throw e;

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
				throw e;
			}
		}

		return list;
	}

	@Override
	public Aircraft insert(Aircraft template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Aircraft aircraft = null;

		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();

			logger.trace(Messages.PROCESS_CREATE_STATEMENT);
			st = conn.prepareStatement(SQL_REQUEST_INSERT, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, template.getRegistrationNumber());
			st.setString(2, template.getModel());
			st.setString(3, template.getCompany());
			st.execute();

			logger.trace(Messages.PROCESS_GET_RESULT_SET);
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				aircraft = new Aircraft(rs.getLong(COLUMN_ID), rs.getString(COLUMN_REGISTRATION_NUMBER),
						rs.getString(COLUMN_MODEL), rs.getString(COLUMN_COMPANY), new HashSet<>(template.getSeats()));
			}

			logger.trace(Messages.SUCCESS_EXECUTED);

		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
			throw e;

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
				throw e;
			}
		}

		return aircraft;
	}

	@Override
	public Aircraft update(Aircraft template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Aircraft aircraft = null;

		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();

			logger.trace(Messages.PROCESS_CREATE_STATEMENT);
			st = conn.prepareStatement(SQL_REQUEST_UPDATE, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, template.getRegistrationNumber());
			st.setString(2, template.getModel());
			st.setString(3, template.getCompany());
			st.setLong(4, template.getId());
			st.execute();

			logger.trace(Messages.PROCESS_GET_RESULT_SET);
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				aircraft = new Aircraft(rs.getLong(COLUMN_ID), rs.getString(COLUMN_REGISTRATION_NUMBER),
						rs.getString(COLUMN_MODEL), rs.getString(COLUMN_COMPANY), new HashSet<>(template.getSeats()));
			}

			logger.trace(Messages.SUCCESS_EXECUTED);

		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
			throw e;

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
				throw e;
			}
		}

		return aircraft;
	}

	@Override
	public Aircraft delete(Aircraft template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Aircraft aircraft = null;

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
				aircraft = new Aircraft(rs.getLong(COLUMN_ID), rs.getString(COLUMN_REGISTRATION_NUMBER),
						rs.getString(COLUMN_MODEL), rs.getString(COLUMN_COMPANY), new HashSet<>(template.getSeats()));
			}

			logger.trace(Messages.SUCCESS_EXECUTED);

		} catch (SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
			throw e;

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
				throw e;
			}
		}

		return aircraft;
	}
}
