package com.martincarney.bugTracker.database.task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.martincarney.bugTracker.database.BaseDAO;
import com.martincarney.bugTracker.database.UserDAO;
import com.martincarney.bugTracker.model.common.LazyLoadedObj;
import com.martincarney.bugTracker.model.common.SearchResultItem;
import com.martincarney.bugTracker.model.common.SearchResultSet;
import com.martincarney.bugTracker.model.task.Task;
import com.martincarney.bugTracker.model.user.User;

public class TaskDAO extends BaseDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskDAO.class);
	
	public Task getTask(long taskId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Task task = null;
		
		String sql = "SELECT t.id, t.name, t.description, t.createdby, ui_cb.fullname AS createdby_fullname \n" +
				"FROM task t \n" +
				"	LEFT OUTER JOIN userinformation ui_cb ON (t.createdby = ui_cb.id) \n" +
				"WHERE t.id = ?::bigint; \n";
		
		try {
			ps = getConnection().prepareStatement(sql);
			int i = 1;
			ps.setLong(i++, taskId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				task = new Task(rs.getLong("id"), rs.getString("name"));
				task.setDescription(rs.getString("description"));
				task.setTaskCreator(new User());
				task.getTaskCreator().setId(rs.getLong("createdby"));
				task.getTaskCreator().setFullName(rs.getString("createdby_fullname"));
			}
			
		} catch (SQLException e) {
			logError(logger, e);
		} finally {
			closeAll(ps, rs);
		}
		
		return task;
	}
	
	public void insertTask(Task newTask, User taskCreator) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO task (id, name, description, createdby, updatedby) \n" +
				"VALUES (?::bigint, ?::text, ?::text, ?::bigint, ?::bigint) \n" +
				"RETURNING id; \n";
		
		try {
			ps = getConnection().prepareStatement(sql);
			int i = 1;
			ps.setLong(i++, newTask.getId());
			ps.setString(i++, newTask.getName());
			ps.setString(i++, newTask.getDescription());
			ps.setLong(i++, taskCreator.getId());
			ps.setLong(i++, taskCreator.getId());
			
			rs = ps.executeQuery();
			if (rs.next()) {
				newTask.setId(rs.getLong("id"));
			}
		} catch (SQLException e) {
			logError(logger, e);
		} finally {
			closeAll(ps);
		}
	}
	
	public void updateTask(Task updatedTask, User updatedBy) {
		PreparedStatement ps = null;
		
		String sql = "UPDATE task \n" +
				"SET name = ?::text, " +
				"	description = ?::text, \n" +
				"	updatedby = ?::bigint \n" +
				"WHERE id = ?::bigint; \n";
		
		try {
			ps = getConnection().prepareStatement(sql);
			int i = 1;
			ps.setString(i++, updatedTask.getName());
			ps.setString(i++, updatedTask.getDescription());
			ps.setLong(i++, updatedBy.getId());
			ps.setLong(i++, updatedTask.getId());
			
			ps.execute();
		} catch (SQLException e) {
			logError(logger, e);
		} finally {
			closeAll(ps);
		}
	}
	
	public SearchResultSet getTaskList() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		SearchResultSet result = new SearchResultSet(Task.class, "Description");
		SearchResultItem task = null;
		
		String sql = "SELECT t.id, t.name, t.description \n" +
				"FROM task t \n" +
				"ORDER BY t.name ASC, t.id DESC; \n";
		
		try {
			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				task = new SearchResultItem(rs.getLong("id"), rs.getString("name"), Task.class, rs.getString("description"));
				result.getSearchResults().add(task);
			}
			
		} catch (SQLException e) {
			logError(logger, e);
		} finally {
			closeAll(ps, rs);
		}
		
		return result;
	}
	
	protected Task buildTask(ResultSet rs) throws SQLException {
		Task task = new Task(rs.getLong("id"), rs.getString("name"));
		task.setDescription(rs.getString("description"));
		
		return task;
	}
}
