package com.userlist.pojos;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AccountDetailsMapper implements RowMapper<AccountDetails> {
	
	@Override
    public AccountDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

		AccountDetails accountDetails = new AccountDetails();
		accountDetails.setUserName(rs.getString("username"));
		accountDetails.setAccountId(rs.getString("accountId"));
		accountDetails.setRole(rs.getInt("role"));

        return accountDetails;

    }


}
