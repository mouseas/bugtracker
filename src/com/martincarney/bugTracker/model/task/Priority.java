package com.martincarney.bugTracker.model.task;

import org.apache.commons.lang3.NotImplementedException;

import com.martincarney.bugTracker.model.common.ObjectWithNameAndId;

public class Priority implements ObjectWithNameAndId {
	
	private long id;
	private String name;
	private long priorityValue;
	
	public Priority(long id, String name, long priorityValue) {
		this.id = id;
		this.name = name;
		throw new NotImplementedException(this.getClass().getName());
	}
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public long getPriorityValue() {
		return priorityValue;
	}
	
	public void setPriorityValue(long priorityValue) {
		this.priorityValue = priorityValue;
	}
	
}
