package com.userlist.jdbc;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<User> fetchAllUsers();
	
	
	
}
