package com.martincarney.bugTracker.model.app;

import java.util.HashMap;
import java.util.Map;

import com.martincarney.bugTracker.model.task.Category;
import com.martincarney.bugTracker.model.task.FlowState;
import com.martincarney.bugTracker.model.task.Priority;
import com.martincarney.bugTracker.model.task.Project;
import com.martincarney.bugTracker.model.task.TaskLog;
import com.martincarney.bugTracker.model.task.TaskTag;

/**
 * Cache of objects which are used throughout the app. Objects cached here
 * should be things which are small in number, which every user needs to load,
 * and which only change through administrator-level changes. Most of these
 * objects should be changed less than once/day on average once initial
 * configuration is done.
 * 
 * @author Martin Carney
 */
public class AppCache {
	
	private AppCache() throws InstantiationException {
		throw new InstantiationException();
	}
	
	private static Map<Long, Category> categories;
	
	private static Map<Long, FlowState> flowStates;
	
	private static Map<Long, Priority> priorities;
	
	private static Map<Long, Project> projects;
	
	private static Map<Long, TaskTag> taskTags;
	
	public static Map<Long, Category> getCategories() {
		if (categories == null) {
			categories = new HashMap<Long, Category>();
		}
		return categories;
	}
	
	public static Map<Long, FlowState> getFlowStates() {
		if (flowStates == null) {
			flowStates = new HashMap<Long, FlowState>();
		}
		return flowStates;
	}
	
	public static Map<Long, Priority> getPriorities() {
		if (priorities == null) {
			priorities = new HashMap<Long, Priority>();
		}
		return priorities;
	}
	
	public static Map<Long, Project> getProjects() {
		if (projects == null) {
			projects = new HashMap<Long, Project>();
		}
		return projects;
	}
	
	public static Map<Long, TaskTag> getTaskTags() {
		if (taskTags == null) {
			taskTags = new HashMap<Long, TaskTag>();
		}
		return taskTags;
	}
	
}
