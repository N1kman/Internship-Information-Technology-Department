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

import com.crud.app.entities.Seat;
import com.crud.app.services.SeatDAO;
import com.crud.app.utils.ServicePropsUtils;

public class SeatDAOImpl implements SeatDAO {
	
	ServiceConnectionFactory serviceConnectionFactory = new ServiceConnectionFactory();
	
	public static final Logger logger = LoggerFactory.getLogger(ServicePropsUtils.class.getName());
	
	public static final String SQLRequestFindAll = "SELECT id, seat_number, seat_type, id_aircraft FROM seat";
	
	public static final String SQLRequestFindById = "SELECT id, seat_number, seat_type, id_aircraft FROM seat WHERE id=?";
	
	public static final String SQLRequestInsert = "INSERT INTO seat (seat_number, seat_type, id_aircraft) VALUES (?, ?, ?)";
	
	public static final String SQLRequestUpdate = "UPDATE seat SET seat_number=?, seat_type=?, id_aircraft=? WHERE id=?";
	
	public static final String SQLRequestDelete = "DELETE FROM seat WHERE id=?";

	@Override
	public Seat findById(long id) throws SQLException {
		Connection conn = null;
		Seat seat = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			logger.trace("Open connection");
			conn = serviceConnectionFactory.openConnection();
			try {
				logger.trace("Create prepared statement");
				st = conn.prepareStatement(SQLRequestFindById);
				st.setLong(1, id);
				try {
					logger.trace("Get result set");
					rs = st.executeQuery();
					if(rs.next()) {
						seat = new Seat(rs.getLong("id"), rs.getString("seat_number"), rs.getString("seat_type"), rs.getLong("id_aircraft"));
						logger.trace("Read seat: ", seat);
					}
				} finally {
					try {
						rs.close();
						logger.trace("Result set closed");
					} catch(SQLException e) {
						logger.error("Cannot close result set", e);
					}
				}
			} finally {
				try {
					serviceConnectionFactory.closeConnection(conn);
					logger.trace("Prepared statement closed");
				} catch(SQLException e) {
					logger.error("Cannot close prepared statement", e);
				}
			}	
		} catch(SQLException e) {
			logger.error("Cannot findById seat", e);
			throw new SQLException("Cannot findById seat", e);
		} finally {
			try {
				serviceConnectionFactory.closeConnection(conn);
				logger.trace("Connection closed");
			} catch(SQLException e) {
				logger.error("Cannot close connection", e);
			}
		}
		
		
		return seat;
	}

	@Override
	public List<Seat> findAll() throws SQLException {
		Connection conn = null;
		Seat seat = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Seat> list= new LinkedList<Seat>();
		
		try {
			logger.trace("Open connection");
			conn = serviceConnectionFactory.openConnection();
			try {
				logger.trace("Create prepared statement");
				st = conn.prepareStatement(SQLRequestFindAll);
				try {
					logger.trace("Get result set");
					rs = st.executeQuery();
					while(rs.next()) {
						seat = new Seat(rs.getLong("id"), rs.getString("seat_number"), rs.getString("seat_type"), rs.getLong("id_aircraft"));
						list.add(seat);
					}
					logger.trace("Read seat list: ", list);
				} finally {
					try {
						rs.close();
						logger.trace("Result set closed");
					} catch(SQLException e) {
						logger.error("Cannot close result set", e);
					}
				}
			} finally {
				try {
					serviceConnectionFactory.closeConnection(conn);
					logger.trace("Prepared statement closed");
				} catch(SQLException e) {
					logger.error("Cannot close prepared statement", e);
				}
			}
			
		} catch(SQLException e) {
			logger.error("Cannot findAll seat", e);
			throw new SQLException("Cannot findAll seat", e);
		} finally {
			try {
				serviceConnectionFactory.closeConnection(conn);
				logger.trace("Connection closed");
			} catch(SQLException e) {
				logger.error("Cannot close connection", e);
			}
		}
		
		return list;
	}

	@Override
	public Seat insert(Seat template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			logger.trace("Open connection");
			conn = serviceConnectionFactory.openConnection();
			try {
				logger.trace("Create prepared statement");
				st = conn.prepareStatement(SQLRequestInsert, Statement.RETURN_GENERATED_KEYS);
				st.setString(1, template.getSeatNumber());
				st.setString(2, template.getSeatType());
				st.setLong(3, template.getAircraftId());
				st.execute();
				try {
					logger.trace("Get result set");
					rs = st.getGeneratedKeys();
					rs.next();
					template.setId(rs.getLong("id"));
					logger.trace("Insert seat with id=" + template.getId());
				} finally {
					try {
						rs.close();
						logger.trace("Result set closed");
					} catch(SQLException e) {
						logger.error("Cannot close result set", e);
					}
				}
			} finally {
				try {
					serviceConnectionFactory.closeConnection(conn);
					logger.trace("Prepared statement closed");
				} catch(SQLException e) {
					logger.error("Cannot close prepared statement", e);
				}
			}	
		} catch(SQLException e) {
			logger.error("Cannot insert seat", e);
			throw new SQLException("Cannot insert seat", e);
		} finally {
			try {
				serviceConnectionFactory.closeConnection(conn);
				logger.trace("Connection closed");
			} catch(SQLException e) {
				logger.error("Cannot close connection", e);
			}
		}
		
		return template;
	}

	@Override
	public Seat update(Seat template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Seat seat = null;
		
		try {
			logger.trace("Open connection");
			conn = serviceConnectionFactory.openConnection();
			try {
				logger.trace("Create prepared statement");
				st = conn.prepareStatement(SQLRequestUpdate, Statement.RETURN_GENERATED_KEYS);
				st.setString(1, template.getSeatNumber());
				st.setString(2, template.getSeatType());
				st.setLong(3, template.getAircraftId());
				st.setLong(4, template.getId());
				st.execute();
				try {
					logger.trace("Get result set");
					rs = st.getGeneratedKeys();
					rs.next();
					seat = new Seat(rs.getLong("id"), rs.getString("seat_number"), rs.getString("seat_type"), rs.getLong("id_aircraft"));
					logger.trace("Update seat " + seat);
				} finally {
					try {
						rs.close();
						logger.trace("Result set closed");
					} catch(SQLException e) {
						logger.error("Cannot close result set", e);
					}
				}
			} finally {
				try {
					serviceConnectionFactory.closeConnection(conn);
					logger.trace("Prepared statement closed");
				} catch(SQLException e) {
					logger.error("Cannot close prepared statement", e);
				}
			}	
		} catch(SQLException e) {
			logger.error("Cannot update seat", e);
			throw new SQLException("Cannot update seat", e);
		} finally {
			try {
				serviceConnectionFactory.closeConnection(conn);
				logger.trace("Connection closed");
			} catch(SQLException e) {
				logger.error("Cannot close connection", e);
			}
		}
		
		return seat;
	}

	@Override
	public Seat delete(Seat template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Seat seat = null;
		
		try {
			logger.trace("Open connection");
			conn = serviceConnectionFactory.openConnection();
			try {
				logger.trace("Create prepared statement");
				st = conn.prepareStatement(SQLRequestDelete, Statement.RETURN_GENERATED_KEYS);
				st.setLong(1, template.getId());
				st.execute();
				try {
					logger.trace("Get result set");
					rs = st.getGeneratedKeys();
					rs.next();
					seat = new Seat(rs.getLong("id"), rs.getString("seat_number"), rs.getString("seat_type"), rs.getLong("id_aircraft"));
					logger.trace("Delete seat " + seat);
				} finally {
					try {
						rs.close();
						logger.trace("Result set closed");
					} catch(SQLException e) {
						logger.error("Cannot close result set", e);
					}
				}
			} finally {
				try {
					serviceConnectionFactory.closeConnection(conn);
					logger.trace("Prepared statement closed");
				} catch(SQLException e) {
					logger.error("Cannot close prepared statement", e);
				}
			}	
		} catch(SQLException e) {
			logger.error("Cannot delete seat", e);
			throw new SQLException("Cannot delete seat", e);
		} finally {
			try {
				serviceConnectionFactory.closeConnection(conn);
				logger.trace("Connection closed");
			} catch(SQLException e) {
				logger.error("Cannot close connection", e);
			}
		}
		
		return seat;
	}

}
