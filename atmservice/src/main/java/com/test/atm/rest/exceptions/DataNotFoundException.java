package com.test.atm.rest.exceptions;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4011026529709934986L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
