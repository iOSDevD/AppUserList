package com.userlist.home;

import java.io.Serializable;

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
