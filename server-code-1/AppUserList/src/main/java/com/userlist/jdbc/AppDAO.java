package com.userlist.jdbc;

import java.util.List;

import com.auth0.jwt.interfaces.DecodedJWT;
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
	
	public AccountDetails validateAccountWithToken(DecodedJWT token);
	
}
