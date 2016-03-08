package com.martincarney.bugTracker.model.common;

public class SearchResultItem implements ObjectWithNameAndId {
	
	private String name;
	private long id;
	private Class<? extends Object> type;
	
	private String[] additionalAttributes;
	
	public SearchResultItem(long id, String name, Class<? extends Object> type, String... additionalAttributes) {
		this.name = name;
		this.id = id;
		this.type = type;
		this.additionalAttributes = additionalAttributes;
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
	
	public Class<? extends Object> getType() {
		return type;
	}
	
	public void setType(Class<? extends Object> type) {
		this.type = type;
	}

	public String[] getAdditionalAttributes() {
		return additionalAttributes;
	}

	public void setAdditionalAttributes(String[] additionalAttributes) {
		this.additionalAttributes = additionalAttributes;
	}
}
