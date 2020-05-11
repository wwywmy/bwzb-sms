package com.bwzb.sms.exception;

public class MailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -264555747582714425L;

	public MailException() {
		super();
	}
	
	public MailException(String message) {
		super(message);
	}

}
