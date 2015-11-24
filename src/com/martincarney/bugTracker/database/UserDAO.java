package com.martincarney.bugTracker.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.martincarney.bugTracker.model.user.User;


public class UserDAO extends BaseDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	public void insertUser(User newUser) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO userinformation (username, fullname, emailaddress, phone, address) \n" +
				"VALUES (?::text, ?::text, ?::text, ?::text, ?::text) \n" +
				"RETURNING id; \n";
		
		try {
			ps = getConnection().prepareStatement(sql);
			int i = 1;
			ps.setString(i++, newUser.getUsername());
			ps.setString(i++, newUser.getFullName());
			ps.setString(i++, newUser.getEmailAddress());
			ps.setString(i++, newUser.getPhoneNumber());
			ps.setString(i++, newUser.getAddress());
			
			rs = ps.executeQuery();
			rs.next();
			newUser.setId(rs.getLong("id"));
		} catch (SQLException e) {
			logError(logger, e);
		} finally {
			closeAll(ps, rs);
		}
	}
}
