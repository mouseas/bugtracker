package com.martincarney.bugTracker.model.user;

import com.martincarney.bugTracker.model.common.ObjectWithID;

public class User implements ObjectWithID {
	
	private long id;
	
	private String username;
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}
