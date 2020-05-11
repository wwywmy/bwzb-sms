package com.bwzb.sms.exception;

public class SmsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7129970188295450756L;
	
	public SmsException() {
		super();
	}
	
	public SmsException(String message) {
		super(message);
	}

}
