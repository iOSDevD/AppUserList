package com.userlist.home;

public class InvalidUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public InvalidUserException(String message){
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
