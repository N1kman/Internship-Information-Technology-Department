package com.crud.app.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crud.app.entities.Aircraft;
import com.crud.app.entities.Seat;
import com.crud.app.exceptions.DAOException;
import com.crud.app.exceptions.Messages;
import com.crud.app.services.SeatDAO;

public class SeatDAOImpl implements SeatDAO {
	
	ServiceConnectionFactory serviceConnectionFactory = new ServiceConnectionFactory();
	
	public static final Logger logger = LoggerFactory.getLogger(SeatDAOImpl.class);
	
	public static final String SQL_REQUEST_FIND_ALL = "SELECT seat.id, seat.seat_number, seat.seat_type, aircraft.id AS id_aircraft, aircraft.registration_number, aircraft.model, aircraft.company FROM seat LEFT JOIN aircraft ON seat.id_aircraft=aircraft.id";	
	public static final String SQL_REQUEST_FIND_BY_ID = "SELECT seat.id, seat.seat_number, seat.seat_type, aircraft.id AS id_aircraft, aircraft.registration_number, aircraft.model, aircraft.company FROM seat LEFT JOIN aircraft ON seat.id_aircraft=aircraft.id WHERE seat.id=?";
	public static final String SQL_REQUEST_INSERT = "INSERT INTO seat (seat_number, seat_type, id_aircraft) VALUES (?, ?, ?)";
	public static final String SQL_REQUEST_UPDATE = "UPDATE seat SET seat_number=?, seat_type=?, id_aircraft=? WHERE id=?";
	public static final String SQL_REQUEST_DELETE = "DELETE FROM seat WHERE id=?";
	
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_SEAT_NUMBER = "seat_number";
	public static final String COLUMN_SEAT_TYPE = "seat_type";
	
	public static final String COLUMN_ID_AIRCRAFT = "id_aircraft";
	public static final String COLUMN_REGISTRATION_NUMBER = "registration_number";
	public static final String COLUMN_MODEL = "model";
	public static final String COLUMN_COMPANY = "company";

	@Override
	public Seat findById(long id) throws DAOException {
		Connection conn = null;
		Seat seat = null;
		Aircraft aircraft = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();
			try {
				logger.trace(Messages.PROCESS_CREATE_STATEMENT);
				st = conn.prepareStatement(SQL_REQUEST_FIND_BY_ID);
				st.setLong(1, id);
				try {
					logger.trace(Messages.PROCESS_GET_RESULT_SET);
					rs = st.executeQuery();
					if(rs.next()) {
						aircraft = new Aircraft(rs.getLong(COLUMN_ID_AIRCRAFT), rs.getString(COLUMN_REGISTRATION_NUMBER), rs.getString(COLUMN_MODEL), rs.getString(COLUMN_COMPANY), null);
						seat = new Seat(rs.getLong(COLUMN_ID), rs.getString(COLUMN_SEAT_NUMBER), rs.getString(COLUMN_SEAT_TYPE), aircraft);
						logger.trace(Messages.SUCCESS_EXECUTED);
					}
				} finally {
					try {
						rs.close();
						logger.trace(Messages.SUCCESS_RESULT_SET_CLOSED);
					} catch(SQLException e) {
						logger.error(Messages.UNSUCCESS_RESULT_SET_CLOSED, e);
					}
				}
			} finally {
				try {
					st.close();
					logger.trace(Messages.SUCCESS_STATEMENT_CLOSED);
				} catch(SQLException e) {
					logger.error(Messages.UNSUCCESS_STATEMENT_CLOSED, e);
				}
			}	
		} catch(SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
			throw new DAOException(Messages.UNSUCCESS_EXECUTED);
		} finally {
			
			try {
				serviceConnectionFactory.closeConnection(conn);
				logger.trace(Messages.SUCCESS_CONNECTION_CLOSED);
			} catch(SQLException e) {
				logger.error(Messages.UNSUCCESS_CONNECTION_CLOSED, e);
			}
		}
		
		
		return seat;
	}

	@Override
	public List<Seat> findAll() throws DAOException {
		Connection conn = null;
		Seat seat = null;
		Aircraft aircraft = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Seat> list= new LinkedList<>();
		
		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();
			try {
				logger.trace(Messages.PROCESS_CREATE_STATEMENT);
				st = conn.prepareStatement(SQL_REQUEST_FIND_ALL);
				try {
					logger.trace(Messages.PROCESS_GET_RESULT_SET);
					rs = st.executeQuery();
					while(rs.next()) {
						aircraft = new Aircraft(rs.getLong(COLUMN_ID_AIRCRAFT), rs.getString(COLUMN_REGISTRATION_NUMBER), rs.getString(COLUMN_MODEL), rs.getString(COLUMN_COMPANY), null);
						seat = new Seat(rs.getLong(COLUMN_ID), rs.getString(COLUMN_SEAT_NUMBER), rs.getString(COLUMN_SEAT_TYPE), aircraft);
						list.add(seat);
					}
					logger.trace(Messages.SUCCESS_EXECUTED);
				} finally {
					try {
						rs.close();
						logger.trace(Messages.SUCCESS_RESULT_SET_CLOSED);
					} catch(SQLException e) {
						logger.error(Messages.UNSUCCESS_RESULT_SET_CLOSED, e);
					}
				}
			} finally {
				try {
					st.close();
					logger.trace(Messages.SUCCESS_STATEMENT_CLOSED);
				} catch(SQLException e) {
					logger.error(Messages.UNSUCCESS_STATEMENT_CLOSED, e);
				}
			}
			
		} catch(SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
			throw new DAOException(Messages.UNSUCCESS_EXECUTED);
		} finally {
			try {
				serviceConnectionFactory.closeConnection(conn);
				logger.trace(Messages.SUCCESS_CONNECTION_CLOSED);
			} catch(SQLException e) {
				logger.error(Messages.UNSUCCESS_CONNECTION_CLOSED, e);
			}
		}
		
		return list;
	}

	@Override
	public Seat insert(Seat template) throws DAOException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Seat seat = null;
		
		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();
			try {
				logger.trace(Messages.PROCESS_CREATE_STATEMENT);
				st = conn.prepareStatement(SQL_REQUEST_INSERT, Statement.RETURN_GENERATED_KEYS);
				st.setString(1, template.getSeatNumber());
				st.setString(2, template.getSeatType());
				st.setLong(3, template.getAircraft().getId());
				st.execute();
				try {
					logger.trace(Messages.PROCESS_GET_RESULT_SET);
					rs = st.getGeneratedKeys();
					rs.next();
					seat = new Seat(rs.getLong(COLUMN_ID), rs.getString(COLUMN_SEAT_NUMBER), rs.getString(COLUMN_SEAT_TYPE), new Aircraft(template.getAircraft()));
					logger.trace(Messages.SUCCESS_EXECUTED);
				} finally {
					try {
						rs.close();
						logger.trace(Messages.SUCCESS_RESULT_SET_CLOSED);
					} catch(SQLException e) {
						logger.error(Messages.UNSUCCESS_RESULT_SET_CLOSED, e);
					}
				}
			} finally {
				try {
					st.close();
					logger.trace(Messages.SUCCESS_STATEMENT_CLOSED);
				} catch(SQLException e) {
					logger.error(Messages.UNSUCCESS_STATEMENT_CLOSED, e);
				}
			}	
		} catch(SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
			throw new DAOException(Messages.UNSUCCESS_EXECUTED);
		} finally {
			try {
				serviceConnectionFactory.closeConnection(conn);
				logger.trace(Messages.SUCCESS_CONNECTION_CLOSED);
			} catch(SQLException e) {
				logger.error(Messages.UNSUCCESS_CONNECTION_CLOSED, e);
			}
		}
		
		return seat;
	}

	@Override
	public Seat update(Seat template) throws DAOException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Seat seat = null;
		
		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();
			try {
				logger.trace(Messages.PROCESS_CREATE_STATEMENT);
				st = conn.prepareStatement(SQL_REQUEST_UPDATE, Statement.RETURN_GENERATED_KEYS);
				st.setString(1, template.getSeatNumber());
				st.setString(2, template.getSeatType());
				st.setLong(3, template.getAircraft().getId());
				st.setLong(4, template.getId());
				st.execute();
				try {
					logger.trace(Messages.PROCESS_GET_RESULT_SET);
					rs = st.getGeneratedKeys();
					rs.next();
					seat = new Seat(rs.getLong(COLUMN_ID), rs.getString(COLUMN_SEAT_NUMBER), rs.getString(COLUMN_SEAT_TYPE), new Aircraft(template.getAircraft()));
					logger.trace(Messages.SUCCESS_EXECUTED);
				} finally {
					try {
						rs.close();
						logger.trace(Messages.SUCCESS_RESULT_SET_CLOSED);
					} catch(SQLException e) {
						logger.error(Messages.UNSUCCESS_RESULT_SET_CLOSED, e);
					}
				}
			} finally {
				try {
					st.close();
					logger.trace(Messages.SUCCESS_STATEMENT_CLOSED);
				} catch(SQLException e) {
					logger.error(Messages.UNSUCCESS_STATEMENT_CLOSED, e);
				}
			}	
		} catch(SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
			throw new DAOException(Messages.UNSUCCESS_EXECUTED);
		} finally {
			try {
				serviceConnectionFactory.closeConnection(conn);
				logger.trace(Messages.SUCCESS_CONNECTION_CLOSED);
			} catch(SQLException e) {
				logger.error(Messages.UNSUCCESS_CONNECTION_CLOSED, e);
			}
		}
		
		return seat;
	}

	@Override
	public Seat delete(Seat template) throws DAOException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Seat seat = null;
		
		try {
			logger.trace(Messages.PROCESS_OPEN_CONNECTION);
			conn = serviceConnectionFactory.openConnection();
			try {
				logger.trace(Messages.PROCESS_CREATE_STATEMENT);
				st = conn.prepareStatement(SQL_REQUEST_DELETE, Statement.RETURN_GENERATED_KEYS);
				st.setLong(1, template.getId());
				st.execute();
				try {
					logger.trace(Messages.PROCESS_GET_RESULT_SET);
					rs = st.getGeneratedKeys();
					rs.next();
					seat = new Seat(rs.getLong(COLUMN_ID), rs.getString(COLUMN_SEAT_NUMBER), rs.getString(COLUMN_SEAT_TYPE), new Aircraft(template.getAircraft()));
					logger.trace(Messages.SUCCESS_EXECUTED);
				} finally {
					try {
						rs.close();
						logger.trace(Messages.SUCCESS_RESULT_SET_CLOSED);
					} catch(SQLException e) {
						logger.error(Messages.UNSUCCESS_RESULT_SET_CLOSED, e);
					}
				}
			} finally {
				try {
					st.close();
					logger.trace(Messages.SUCCESS_STATEMENT_CLOSED);
				} catch(SQLException e) {
					logger.error(Messages.UNSUCCESS_STATEMENT_CLOSED, e);
				}
			}	
		} catch(SQLException e) {
			logger.error(Messages.UNSUCCESS_EXECUTED, e);
			throw new DAOException(Messages.UNSUCCESS_EXECUTED);
		} finally {
			try {
				serviceConnectionFactory.closeConnection(conn);
				logger.trace(Messages.SUCCESS_CONNECTION_CLOSED);
			} catch(SQLException e) {
				logger.error(Messages.UNSUCCESS_CONNECTION_CLOSED, e);
			}
		}
		
		return seat;
	}

}
