package com.martincarney.bugTracker.form.task;

import com.martincarney.bugTracker.form.BaseForm;
import com.martincarney.bugTracker.model.common.ObjectWithNameAndId;

public class TaskForm extends BaseForm implements ObjectWithNameAndId {
	
	protected long id;
	protected String name;
	protected String description;
	
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
