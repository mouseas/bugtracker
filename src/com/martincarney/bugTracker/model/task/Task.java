package com.martincarney.bugTracker.model.task;

import com.martincarney.bugTracker.model.common.ObjectWithID;

public class Task implements ObjectWithID {
	
	private long id;
	
	private String name;
	
	private String description;
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
