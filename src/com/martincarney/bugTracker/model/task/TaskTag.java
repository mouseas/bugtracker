package com.martincarney.bugTracker.model.task;

import org.apache.commons.lang3.NotImplementedException;

import com.martincarney.bugTracker.model.common.ObjectWithNameAndId;

public class TaskTag implements ObjectWithNameAndId {
	
	private long id;
	private String name;
	
	public TaskTag(long id, String name) {
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
	
}
