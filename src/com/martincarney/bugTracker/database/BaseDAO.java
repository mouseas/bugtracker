package com.martincarney.bugTracker.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;

/**
 * Base class for each DAO (Database Access Object) class. Contains common methods used
 * by child DAO classes.
 * @author Martin
 */
public abstract class BaseDAO {
	
	protected Connection conn;
	
	/**
	 * <p>Opens a {@link Connection} to the PostgreSQL database. If a connection is already
	 * open, this method simply returns the existing, open connection.</p>
	 * <p>Must be called before accessing the database, typically at the start of
	 * each DAO method.</p>
	 * @param databaseName name of the database to connect to. If null, the schema database
	 * is connected to; this is useful for creating new databases.
	 * @throws SQLException if a database exception occurs.
	 * @return {@link #conn}, returned for ease of use.
	 */
	protected Connection getConnection(String databaseName) throws SQLException {
		if (databaseName == null) {
			// prevent creating a database with name "null" when trying to connect to no database
			databaseName = DBConst.SCHEMA_DATABASE_NAME;
		}
		
		if (conn == null || conn.isClosed()) {
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + databaseName,
						DBConst.DEFAULT_DATABASE_USERNAME, SecurityConstants.POSTGRESQL_PASSWORD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	/**
	 * <p>Opens a {@link Connection} to the PostgreSQL database. If a connection is already
	 * open, this method simply returns the existing, open connection.</p>
	 * <p>Must be called before accessing the database, typically at the start of
	 * each DAO method.</p>
	 * @throws SQLException if a database exception occurs.
	 * @return {@link #conn}, returned for ease of use.
	 */
	protected Connection getConnection() throws SQLException {
		return getConnection(DBConst.DEFAULT_DATABASE_NAME);
	}
	
	/**
	 * Checks if the specified table exists
	 * @param tableName
	 * @return {@code true} if the table exists. {@code false} if either the table
	 * doesn't exist, or an error occurred.
	 */
	protected boolean doesTableExist(String tableName) {
		ResultSet rs = null;
		
		try {
			getConnection();
			rs = conn.getMetaData().getTables(null, null, tableName.toLowerCase(), null);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs);
		}
		return false;
	}
	
	/**
	 * Checks if the specified table has the specified column.
	 * @param tableName
	 * @param columnName
	 * @return {@code true} if both the table and column exist. {@code false} if either
	 * the column doesn't exist, the table doesn't exist, or an error occurred.
	 */
	protected boolean doesTableColumnExist(String tableName, String columnName) {
		ResultSet rs = null;
		
		try {
			getConnection();
			rs = conn.getMetaData().getColumns(null, null, tableName.toLowerCase(), columnName.toLowerCase());
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs);
		}
		return false;
	}
	
	/**
	 * Closes the {@link Connection} assigned to {@link #conn}, if any is open.
	 * @throws SQLException if a database exception occurs.
	 */
	protected void closeConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the items used in an individual query, but leaves the database connection open.
	 * @param ps
	 */
	protected void closeQuery(PreparedStatement ps) {
		closeQuery(ps, null);
	}
	
	/**
	 * Closes the items used in an individual query, but leaves the database connection open.
	 * @param rs
	 */
	protected void closeQuery(ResultSet rs) {
		closeQuery(null, rs);
	}
	
	/**
	 * Closes the items used in an individual query, but leaves the database connection open.
	 */
	protected void closeQuery(ResultSet rs, PreparedStatement ps) {
		closeQuery(ps, rs);
	}
	
	/**
	 * Closes the items used in an individual query, but leaves the database connection open.
	 * @param ps
	 * @param rs
	 */
	protected void closeQuery(PreparedStatement ps, ResultSet rs) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Closes the items used in an individual query, then closes the database connection.
	 * @param ps
	 */
	protected void closeAll(PreparedStatement ps) {
		closeAll(ps, null);
	}
	
	/**
	 * Closes the items used in an individual query, then closes the database connection.
	 * @param rs
	 */
	protected void closeAll(ResultSet rs) {
		closeAll(null, rs);
	}
	
	/**
	 * Closes the items used in an individual query, then closes the database connection.
	 * @param rs
	 * @param ps
	 */
	protected void closeAll(ResultSet rs, PreparedStatement ps) {
		closeAll(ps, rs);
	}
	
	/**
	 * Closes the items used in an individual query, then closes the database connection.
	 * @param ps
	 * @param rs
	 */
	protected void closeAll(PreparedStatement ps, ResultSet rs) {
		closeQuery(ps, rs);
		closeConnection();
	}
	
	protected void logError(Logger logger, Throwable e) {
		if (logger == null) {
			e.printStackTrace();
		} else {
			logger.error("Database Error:", e);
		}
	}
}
