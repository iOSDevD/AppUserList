package com.userlist.home;

/**
 * @author Nikunj.Upadhyay
 * 
 * CS-763 Project - AppUserList
 * 
 * Invalid User Exception thrown when User details as missing
 * like first name or last Name
 * 
 */
public class InvalidUserException extends Exception {

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
