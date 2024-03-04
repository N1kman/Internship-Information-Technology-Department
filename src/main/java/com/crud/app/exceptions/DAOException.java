package com.crud.app.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DAOException extends Exception {
	
	private static final long serialVersionUID = 7750652729810388739L;
	
	private String message;
	
	public static final String SUCCESS_CONNECTION_CLOSED = "Connection closed";
	public static final String SUCCESS_STATEMENT_CLOSED = "Statement closed";
	public static final String SUCCESS_RESULT_SET_CLOSED = "Result set closed";
	public static final String SUCCESS_EXECUTED = "Executed";
	
	public static final String UNSUCCESS_CONNECTION_CLOSED = "Cannot close connection";
	public static final String UNSUCCESS_STATEMENT_CLOSED = "Cannot close statement";
	public static final String UNSUCCESS_RESULT_SET_CLOSED = "Cannot close result set";
	public static final String UNSUCCESS_EXECUTED = "Not executed";
	
	public static final String PROCESS_OPEN_CONNECTION = "Open connection";
	public static final String PROCESS_CREATE_STATEMENT = "Create statement";
	public static final String PROCESS_GET_RESULT_SET = "Get result set";
	
	public DAOException(String message) {
		super(message);
		this.message = message;
	}
}
