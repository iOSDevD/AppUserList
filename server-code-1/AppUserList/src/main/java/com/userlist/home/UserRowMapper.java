package com.userlist.home;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
	
	@Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setUserFName(rs.getString("firstName"));
        user.setUserLName(rs.getString("lastName"));

        return user;

    }

}
