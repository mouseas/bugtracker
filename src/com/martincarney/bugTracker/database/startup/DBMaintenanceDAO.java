package com.martincarney.bugTracker.database.startup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.martincarney.bugTracker.database.BaseDAO;
import com.martincarney.bugTracker.database.DBConst;
import com.martincarney.bugTracker.database.UserDAO;
import com.martincarney.bugTracker.model.user.User;

/**
 * 
 * @author Martin
 *
 */
public class DBMaintenanceDAO extends BaseDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(DBMaintenanceDAO.class);
	
	/**
	 * Creates the core database of the app with the minimum data needed for the app
	 * to function.
	 */
	public void initializeDatabase() {
		if (!doesDatabaseExist(DBConst.DEFAULT_DATABASE_NAME)) {
			createDatabase(DBConst.DEFAULT_DATABASE_NAME);
		}
		
		if (!doesTableExist("userinformation")) {
			createUserTable();
		}
		
		if (!doesTableExist("task")) {
			createTaskTable();
		}
	}
	
	/**
	 * <p>Checks if the specified database already exists.</p>
	 * 
	 * <p>While this could go on BaseDAO, it is only used for whole database maintenance, so
	 * it best fits here.</p>
	 * @param databaseName Name of the database to check for the existence of.
	 * @return {@code true} if a successful connection was made to the schema db, and the
	 * specified database does exist. {@code false} otherwise, including if there's a
	 * connection error.
	 * @throws SQLException
	 */
	protected boolean doesDatabaseExist(String databaseName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection(DBConst.SCHEMA_DATABASE_NAME);
			
			String sql = "SELECT datname \n" +
					"FROM pg_database db \n" +
					"WHERE db.datname ILIKE ?::text; \n";
			ps = conn.prepareStatement(sql);
			ps.setString(1, databaseName);
			
			rs = ps.executeQuery();
			if (rs.next() && databaseName.equals(rs.getString("datname"))) {
				return true;
			}
		} catch (SQLException e) {
			logError(logger, e);
		} finally {
			closeAll(ps, rs);
		}
		
		return false;
	}

	/**
	 * Creates a database by the specified name. A check to ensure the database
	 * does not already exist should be made prior to calling this method.
	 * @param databaseName Name of the database to create. This should never
	 * come from user input.
	 * @throws SQLException
	 */
	private void createDatabase(String databaseName) {
		if (databaseName == null) {
			throw new NullPointerException("Must specify a database name");
		}
		PreparedStatement ps = null;
		
		try {
			getConnection(DBConst.SCHEMA_DATABASE_NAME);
			
			logger.info("Creating database \"" + databaseName + "\".");
			String sql = "CREATE DATABASE " + databaseName + " \n";
			
			ps = conn.prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			logError(logger, e);
		} finally {
			closeAll(ps);
		}
	}
	
	private void createUserTable() {
		PreparedStatement ps = null;
		try {
			getConnection();
			
			logger.info("Creating table \"userinformation\".");
			String sql = "-- Table: userinformation \n" +
					" \n" +
					"-- DROP TABLE userinformation; \n" +
					" \n" +
					"CREATE TABLE userinformation ( \n" +
					"	id bigserial NOT NULL, \n" +
					"	username text NOT NULL, \n" +
					"	fullname text NOT NULL, \n" +
					"	emailaddress text, \n" +
					"	phone text, \n" +
					"	address text, \n" +
					"	CONSTRAINT userinformation_pkey PRIMARY KEY (id), \n" +
					"	CONSTRAINT userinformation_emailaddress_key UNIQUE (emailaddress), \n" +
					"	CONSTRAINT userinformation_username_key UNIQUE (username) \n" +
					") \n" +
					"WITH ( \n" +
					"	OIDS=FALSE \n" +
					"); \n" +
					"ALTER TABLE userinformation \n" +
					"	OWNER TO " + DBConst.DEFAULT_DATABASE_USERNAME + "; \n";
			
			ps = conn.prepareStatement(sql);
			ps.execute();
			closeAll(ps);
			
			User sysadmin = new User();
			sysadmin.setUsername("sysadmin");
			sysadmin.setFullName("System Admin");
			UserDAO userDAO = new UserDAO();
			userDAO.insertUser(sysadmin);
		} catch (SQLException e) {
			logError(logger, e);
		} finally {
			closeAll(ps);
		}
	}
	
	private void createTaskTable() {
		PreparedStatement ps = null;
		try {
			getConnection();
			
			logger.info("Creating table \"task\".");
			String sql = "-- Table: task \n" +
					" \n" +
					"-- DROP TABLE task; \n" +
					" \n" +
					"CREATE TABLE task ( \n" +
					"	id bigserial NOT NULL, \n" +
					"	name text NOT NULL, \n" +
					"	description text NOT NULL, \n" +
					"	createdby bigint NOT NULL REFERENCES userinformation (id), \n" +
					"	updatedby bigint NOT NULL REFERENCES userinformation (id), \n" +
					"	CONSTRAINT task_pkey PRIMARY KEY (id) \n" +
					") \n" +
					"WITH ( \n" +
					"	OIDS=FALSE \n" +
					"); \n" +
					"ALTER TABLE task \n" +
					"	OWNER TO " + DBConst.DEFAULT_DATABASE_USERNAME + "; \n";
			
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			logError(logger, e);
		} finally {
			closeAll(ps);
		}
	}
}
