package com.userlist.home;

import java.io.Serializable;

/**
 * @author Nikunj.Upadhyay
 * 
 * CS-763 Project - AppUserList
 * 
 * User that will be access with FirstName and Last Name in 
 * a list after login.
 * 
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userFName;
	private String userLName;
	
	public String getUserLName() {
		return userLName;
	}
	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}
	
	public String getUserFName() {
		return userFName;
	}
	
	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}	
	
}
