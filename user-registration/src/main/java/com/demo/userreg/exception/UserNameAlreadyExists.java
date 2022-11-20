package com.demo.userreg.exception;

public class UserNameAlreadyExists extends ServiceException {

	
	private static final long serialVersionUID = 1L;

	public UserNameAlreadyExists() {
		super();
		
	}

	public UserNameAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UserNameAlreadyExists(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserNameAlreadyExists(String message) {
		super(message);
		
	}

	public UserNameAlreadyExists(Throwable cause) {
		super(cause);
		
	}

}
