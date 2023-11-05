package com.userlist.jdbc;

import com.userlist.home.User;

public interface AppDAO {

	/*
	 * Register user
	 */
	public String registerUserReturnGuid();
	
	/*
	 * Insert App. Data
	 */
	public boolean insertAppData(Object appData);
	
	public boolean insertUser(User user);
	
	
	
}
