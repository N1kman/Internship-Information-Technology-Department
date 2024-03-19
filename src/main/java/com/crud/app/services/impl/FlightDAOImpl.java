package com.crud.app.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crud.app.entities.Aircraft;
import com.crud.app.entities.Flight;
import com.crud.app.entities.Seat;
import com.crud.app.exceptions.Messages;
import com.crud.app.services.FlightDAO;

public class FlightDAOImpl implements FlightDAO {

	ServiceConnectionFactory serviceConnectionFactory = new ServiceConnectionFactory();

	public static final Logger logger = LoggerFactory.getLogger(FlightDAOImpl.class);

	public static final String SQL_REQUEST_FIND_ALL = "SELECT flight.id, flight.code, flight.place_departure, flight.place_arrival, flight.date_departure, flight.date_arrival, aircraft.id AS id_aircraft, aircraft.registration_number, aircraft.model, aircraft.company, seat.id AS id_seat, seat.seat_number, seat.seat_type FROM flight LEFT JOIN aircraft ON aircraft.id=flight.id_aircraft LEFT JOIN seat ON flight.id_aircraft=seat.id_aircraft";
	public static final String SQL_REQUEST_FIND_BY_ID = "SELECT flight.id, flight.code, flight.place_departure, flight.place_arrival, flight.date_departure, flight.date_arrival, aircraft.id AS id_aircraft, aircraft.registration_number, aircraft.model, aircraft.company, seat.id AS id_seat, seat.seat_number, seat.seat_type FROM flight LEFT JOIN aircraft ON aircraft.id=flight.id_aircraft LEFT JOIN seat ON flight.id_aircraft=seat.id_aircraft WHERE flight.id=?";
	public static final String SQL_REQUEST_INSERT = "INSERT INTO flight (code, place_departure, place_arrival, date_departure, date_arrival, id_aircraft) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String SQL_REQUEST_UPDATE = "UPDATE flight SET code=?, place_departure=?, place_arrival=?, date_departure=?, date_arrival=?, id_aircraft=? WHERE id=?";
	public static final String SQL_REQUEST_DELETE = "DELETE FROM flight WHERE id=?";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_CODE = "code";
	public static final String COLUMN_PLACE_DEPARTURE = "place_departure";
	public static final String COLUMN_PLACE_ARRIVAL = "place_arrival";
	public static final String COLUMN_DATE_DEPARTURE = "date_departure";
	public static final String COLUMN_DATE_ARRIVAL = "date_arrival";

	public static final String COLUMN_REGISTRATION_NUMBER = "registration_number";
	public static final String COLUMN_MODEL = "model";
	public static final String COLUMN_COMPANY = "company";

	public static final String COLUMN_ID_SEAT = "id_seat";
	public static final String COLUMN_SEAT_NUMBER = "seat_number";
	public static final String COLUMN_SEAT_TYPE = "seat_type";

	@Override
	public Flight findById(long id) throws SQLException {
		Connection conn = null;
		Aircraft aircraft = null;
		Seat seat = null;
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
				if (flight == null) {
					aircraft = new Aircraft(rs.getLong(COLUMN_ID), rs.getString(COLUMN_REGISTRATION_NUMBER),
							rs.getString(COLUMN_MODEL), rs.getString(COLUMN_COMPANY), new HashSet<>());
					flight = new Flight(rs.getLong(COLUMN_ID), rs.getString(COLUMN_CODE),
							rs.getString(COLUMN_PLACE_DEPARTURE), rs.getString(COLUMN_PLACE_ARRIVAL),
							rs.getTimestamp(COLUMN_DATE_DEPARTURE).toLocalDateTime(),
							rs.getTimestamp(COLUMN_DATE_ARRIVAL).toLocalDateTime(), aircraft);
				}
				if (rs.getLong(COLUMN_ID_SEAT) != 0) {
					seat = new Seat(rs.getLong(COLUMN_ID_SEAT), rs.getString(COLUMN_SEAT_NUMBER),
							rs.getString(COLUMN_SEAT_TYPE), null);
					flight.getAircraft().getSeats().add(seat);
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

		return flight;
	}

	@Override
	public List<Flight> findAll() throws SQLException {
		Connection conn = null;
		Flight flight = null;
		Aircraft aircraft = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Flight> list = new LinkedList<>();

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
				flight = new Flight(rs.getLong(COLUMN_ID), rs.getString(COLUMN_CODE),
						rs.getString(COLUMN_PLACE_DEPARTURE), rs.getString(COLUMN_PLACE_ARRIVAL),
						rs.getTimestamp(COLUMN_DATE_DEPARTURE).toLocalDateTime(),
						rs.getTimestamp(COLUMN_DATE_ARRIVAL).toLocalDateTime(), aircraft);
				if (!list.contains(flight)) {
					list.add(flight);
				}
				Long id = rs.getLong(COLUMN_ID);
				Seat seat = new Seat(rs.getLong(COLUMN_ID_SEAT), rs.getString(COLUMN_SEAT_NUMBER),
						rs.getString(COLUMN_SEAT_TYPE), null);
				if (seat.getId() != 0) {
					list.stream().filter(buffer -> buffer.getAircraft().getId() == id)
							.forEach(buffer -> buffer.getAircraft().getSeats().add(seat));
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
	public Flight insert(Flight template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Flight flight = null;

		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();

			logger.trace(Messages.PROCESS_CREATE_STATEMENT);
			st = conn.prepareStatement(SQL_REQUEST_INSERT, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, template.getCode());
			st.setString(2, template.getPlaceDeparture());
			st.setString(3, template.getPlaceArrival());
			st.setTimestamp(4, Timestamp.valueOf(template.getDateDeparture()));
			st.setTimestamp(5, Timestamp.valueOf(template.getDateArrival()));
			st.setLong(6, template.getAircraft().getId());
			st.execute();

			logger.trace(Messages.PROCESS_GET_RESULT_SET);
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				flight = new Flight(rs.getLong(COLUMN_ID), rs.getString(COLUMN_CODE),
						rs.getString(COLUMN_PLACE_DEPARTURE), rs.getString(COLUMN_PLACE_ARRIVAL),
						rs.getTimestamp(COLUMN_DATE_DEPARTURE).toLocalDateTime(),
						rs.getTimestamp(COLUMN_DATE_ARRIVAL).toLocalDateTime(), new Aircraft(template.getAircraft()));
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

		return flight;
	}

	@Override
	public Flight update(Flight template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Flight flight = null;

		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();

			logger.trace(Messages.PROCESS_CREATE_STATEMENT);
			st = conn.prepareStatement(SQL_REQUEST_UPDATE, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, template.getCode());
			st.setString(2, template.getPlaceDeparture());
			st.setString(3, template.getPlaceArrival());
			st.setTimestamp(4, Timestamp.valueOf(template.getDateDeparture()));
			st.setTimestamp(5, Timestamp.valueOf(template.getDateArrival()));
			st.setLong(6, template.getAircraft().getId());
			st.execute();

			logger.trace(Messages.PROCESS_GET_RESULT_SET);
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				flight = new Flight(rs.getLong(COLUMN_ID), rs.getString(COLUMN_CODE),
						rs.getString(COLUMN_PLACE_DEPARTURE), rs.getString(COLUMN_PLACE_ARRIVAL),
						rs.getTimestamp(COLUMN_DATE_DEPARTURE).toLocalDateTime(),
						rs.getTimestamp(COLUMN_DATE_ARRIVAL).toLocalDateTime(), new Aircraft(template.getAircraft()));
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

		return flight;
	}

	@Override
	public Flight delete(Flight template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Flight flight = null;

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
				flight = new Flight(rs.getLong(COLUMN_ID), rs.getString(COLUMN_CODE),
						rs.getString(COLUMN_PLACE_DEPARTURE), rs.getString(COLUMN_PLACE_ARRIVAL),
						rs.getTimestamp(COLUMN_DATE_DEPARTURE).toLocalDateTime(),
						rs.getTimestamp(COLUMN_DATE_ARRIVAL).toLocalDateTime(), new Aircraft(template.getAircraft()));
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

		return flight;
	}

}
