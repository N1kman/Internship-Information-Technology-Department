package com.crud.app.exceptions;

public class Messages {
	
	private Messages() {
		throw new IllegalStateException("Static class");
	}
	
	public static final String SUCCESS_CONNECTION_CLOSED = "Connection closed";
	public static final String SUCCESS_STATEMENT_CLOSED = "Statement closed";
	public static final String SUCCESS_RESULT_SET_CLOSED = "Result set closed";
	public static final String SUCCESS_EXECUTED = "Executed";
	
	public static final String UNSUCCESS_CLOSED = "Cannot close";
	public static final String UNSUCCESS_EXECUTED = "Not executed";
	
	public static final String PROCESS_OPEN_CONNECTION = "Open connection";
	public static final String PROCESS_CREATE_STATEMENT = "Create statement";
	public static final String PROCESS_GET_RESULT_SET = "Get result set";
}
