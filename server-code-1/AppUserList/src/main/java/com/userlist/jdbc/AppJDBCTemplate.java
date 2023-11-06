package com.userlist.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.userlist.home.User;
import com.userlist.home.UserRowMapper;

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
		String query = String.format("Insert into friends (name) values %s", user.getUserFName()+", "+user.getUserLName());
		this.jdbcTemplate.execute(query);
		return false;
	}
	
	@Override
	public List<User> fetchAllUsers() {
		String query = String.format("Select * from appUsers");
		List<User> users= this.jdbcTemplate.query(query, new UserRowMapper());
		return users;
	}

}
