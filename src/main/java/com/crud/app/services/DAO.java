package com.crud.app.services;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	
	public T findById(long id) throws SQLException;
	
	public List<T> findAll() throws SQLException;
	
	public T insert(T template) throws SQLException;
	
	public T update(T template) throws SQLException;
	
	public T delete(T template) throws SQLException;
	
}
