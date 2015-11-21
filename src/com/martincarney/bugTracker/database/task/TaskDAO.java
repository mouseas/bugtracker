package com.martincarney.bugTracker.database.task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.martincarney.bugTracker.database.BaseDAO;
import com.martincarney.bugTracker.model.common.LazyLoadedObj;
import com.martincarney.bugTracker.model.task.Task;
import com.martincarney.bugTracker.model.user.User;

public class TaskDAO extends BaseDAO {
	
	public Task getTask(long taskId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Task task = null;
		
		String sql = "SELECT t.id, t.name, t.description \n" +
				"FROM task t \n" +
				"WHERE t.id = ?::bigint; \n";
		
		try {
			ps = getConnection().prepareStatement(sql);
			int i = 1;
			ps.setLong(i++, taskId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				task = new Task(rs.getLong("id"), rs.getString("name"));
				task.setDescription(rs.getString("description"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(ps, rs);
		}
		
		return task;
	}
	
	public void insertTask(Task newTask) {
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO task (id, name, description) \n" +
				"VALUES (?::bigint, ?::text, ?::text); \n";
		
		try {
			ps = getConnection().prepareStatement(sql);
			int i = 1;
			ps.setLong(i++, newTask.getId());
			ps.setString(i++, newTask.getName());
			ps.setString(i++, newTask.getDescription());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		} finally {
			closeAll(ps);
		}
	}
	
	public List<LazyLoadedObj<Task>> getTaskList() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		LazyLoadedObj<Task> task = null;
		List<LazyLoadedObj<Task>> result = new LinkedList<LazyLoadedObj<Task>>();
		
		String sql = "SELECT t.id, t.name, t.description \n" +
				"FROM task t \n" +
				"ORDER BY t.name ASC, t.id DESC; \n";
		
		try {
			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				task = new LazyLoadedObj<Task>(rs.getLong("id"), rs.getString("name")) {
					@Override
					public Task getObj() {
						TaskDAO taskDAO = new TaskDAO();
						return taskDAO.getTask(getObjId());
					}
				};
				result.add(task);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
