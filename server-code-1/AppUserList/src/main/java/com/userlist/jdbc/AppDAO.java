package com.userlist.jdbc;

import java.util.List;

import com.userlist.home.LoginUser;
import com.userlist.home.User;
import com.userlist.pojos.AccountDetails;

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
	
	public AccountDetails validateAccount(LoginUser account);
	
}
