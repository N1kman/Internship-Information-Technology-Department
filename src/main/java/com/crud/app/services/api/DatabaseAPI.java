package com.crud.app.services.api;

import java.util.List;

public interface DatabaseAPI<T> {
	
	public T findById(long id);
	
	public List<T> findAll();
	
	public T insert(T template);
	
	public T update(T template);
	
	public T delete(T template);
}
