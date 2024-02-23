package com.crud.app.services;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	
	public T get(long id) throws SQLException;
	
	public List<T> getAll(long id) throws SQLException;
	
	public int save(T temlate) throws SQLException;
	
	public int insert(T temlate) throws SQLException;
	
	public int update(T temlate) throws SQLException;
	
	public int delete(T temlate) throws SQLException;
	
}
