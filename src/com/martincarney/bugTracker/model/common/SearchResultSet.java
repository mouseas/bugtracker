package com.martincarney.bugTracker.model.common;

import java.util.*;

public class SearchResultSet {
	
	private List<SearchResultItem> searchResults;
	private Class<? extends Object> type;
	
	private String[] additionalAttributeNames;
	
	public SearchResultSet(Class<? extends Object> type, String... additionalAttributeNames) {
		this.searchResults = new ArrayList<SearchResultItem>();
		this.type = type;
		this.additionalAttributeNames = additionalAttributeNames;
	}
	
	public List<SearchResultItem> getSearchResults() {
		return searchResults;
	}
	
	public void setSearchResults(List<SearchResultItem> searchResults) {
		this.searchResults = searchResults;
	}
	
	public Class<? extends Object> getType() {
		return type;
	}
	
	public void setType(Class<? extends Object> type) {
		this.type = type;
	}
	
	public String[] getAdditionalAttributeNames() {
		return additionalAttributeNames;
	}
	
	public void setAdditionalAttributeNames(String[] additionalAttributeNames) {
		this.additionalAttributeNames = additionalAttributeNames;
	}
}
