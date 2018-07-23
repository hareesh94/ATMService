package com.test.atm.rest.exceptions;

public class InsufficientBalanceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4719143647706727719L;
	
	public InsufficientBalanceException(String message) {
		super(message);
	}
}
