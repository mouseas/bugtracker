package com.martincarney.bugTracker.model.file;

import com.martincarney.bugTracker.model.common.ObjectWithNameAndId;

public class FileAttachment implements ObjectWithNameAndId {
	
	private long id;
	private String filename;
	
	public FileAttachment(long id, String filename) {
		this.id = id;
		this.filename = filename;
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
		return filename;
	}

	@Override
	public void setName(String name) {
		this.filename = name;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
