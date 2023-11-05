package com.userlist.home;

/**
 * @author Nikunj.Upadhyay
 * 
 * CS-763 Project - AppUserList
 * 
 * LoginUser object used during login with username and password.
 */
public class LoginUser {

	private String username;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
