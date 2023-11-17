package com.userlist.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.userlist.home.LoginUser;
import com.userlist.home.User;
import com.userlist.home.UserRowMapper;
import com.userlist.pojos.AccountDetails;
import com.userlist.pojos.AccountDetailsMapper;

public class AppJDBCTemplate implements AppDAO {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public AppJDBCTemplate(){
		System.out.println("App JDBC Template Constructor");
	}
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated constructor stub
		this.dataSource=ds;
		this.jdbcTemplate=new JdbcTemplate(this.dataSource);
	}
	@Override
	public String registerUserReturnGuid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertAppData(Object appData) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.execute("Insert into friends (name) values ('John')");
		return false;
	}
	

	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		String query = String.format("Insert into appUsers (firstName,lastName) values (?,?)");
		int rowsAffected = this.jdbcTemplate.update(query, user.getUserFName(), user.getUserLName());
		if(rowsAffected == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<User> fetchAllUsers() {
		String query = String.format("Select * from appUsers");
		List<User> users= this.jdbcTemplate.query(query, new UserRowMapper());
		return users;
	}
	
	@Override
	public AccountDetails validateAccount(LoginUser account) {
		String query = String.format("select * from accounts where username=? and password=?");

		List<AccountDetails> details = this.jdbcTemplate.query(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, account.getUsername());
				ps.setString(2, account.getPassword());
				
			}
		}, new AccountDetailsMapper());
		if(details.size() == 1) {
			return details.get(0);
		} else {
			return null;
		}
	}

}
