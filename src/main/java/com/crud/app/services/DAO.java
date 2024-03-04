package com.crud.app.services;

import java.util.List;

import com.crud.app.exceptions.DAOException;

public interface DAO<T> {
	
	public T findById(long id) throws DAOException;
	
	public List<T> findAll() throws DAOException;
	
	public T insert(T template) throws DAOException;
	
	public T update(T template) throws DAOException;
	
	public T delete(T template) throws DAOException;
	
}
