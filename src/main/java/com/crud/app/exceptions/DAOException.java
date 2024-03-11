package com.crud.app.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DAOException extends Exception {
	
	private static final long serialVersionUID = 7750652729810388739L;
	
	public DAOException(String message) {
		super(message);
	}
}
