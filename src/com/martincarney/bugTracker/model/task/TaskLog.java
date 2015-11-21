package com.martincarney.bugTracker.model.task;

import java.util.Calendar;

import com.martincarney.bugTracker.model.common.LazyLoadedObj;
import com.martincarney.bugTracker.model.common.ObjectWithId;
import com.martincarney.bugTracker.model.user.User;

public class TaskLog implements ObjectWithId {
	
	private long id;
	
	private String logText;
	
	private Calendar date;
	
	private double hours;
	
	private LazyLoadedObj<User> creator;
	
	private TaskLog parentLog;
	
	private long taskId;
	
	public TaskLog(long id, long taskId, String logText) {
		this.id = id;
		this.taskId = taskId;
		this.logText = logText;
	}
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLogText() {
		return logText;
	}
	
	public void setLogText(String logText) {
		this.logText = logText;
	}
	
	public Calendar getDate() {
		return date;
	}
	
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	public double getHours() {
		return hours;
	}
	
	public void setHours(double hours) {
		this.hours = hours;
	}
	
	public LazyLoadedObj<User> getCreator() {
		return creator;
	}
	
	public void setCreator(LazyLoadedObj<User> creator) {
		this.creator = creator;
	}
	
	public TaskLog getParentLog() {
		return parentLog;
	}
	
	public void setParentLog(TaskLog parentLog) {
		this.parentLog = parentLog;
	}
	
	public long getTaskId() {
		return taskId;
	}
	
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	
}
