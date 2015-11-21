package com.martincarney.bugTracker.model.task;

import java.util.Calendar;
import java.util.Collection;
import java.util.Set;

import com.martincarney.bugTracker.model.common.LazyLoadedObj;
import com.martincarney.bugTracker.model.common.ObjectWithNameAndId;
import com.martincarney.bugTracker.model.file.FileAttachment;
import com.martincarney.bugTracker.model.user.User;

public class Task implements ObjectWithNameAndId {
	
	private long id;
	
	private String name;
	
	private String description;
	
	private LazyLoadedObj<Category> lazyLoadedCategory;
	
	private LazyLoadedObj<Project> lazyLoadedProject;
	
	private LazyLoadedObj<FlowState> lazyLoadedFlowState;
	
	private Collection<LazyLoadedObj<User>> lazyLoadedAssignedProgrammers;
	
	private Collection<LazyLoadedObj<User>> lazyLoadedAssignedTesters;
	
	private Collection<LazyLoadedObj<User>> lazyLoadedPeopleWantingUpdates;
	
	private LazyLoadedObj<User> lazyLoadedTaskCreator;
	
	private LazyLoadedObj<Task> parentTask;
	
	private Collection<LazyLoadedObj<Task>> childTasks;
	
	private Calendar createdOn;
	
	private Calendar workStartedOn;
	
	private Calendar workFinishedOn;
	
	private LazyLoadedObj<Priority> lazyLoadedPriority;
	
	private Set<TaskTag> tags;
	
	private Collection<LazyLoadedObj<TaskLog>> lazyLoadedTaskLogs;
	
	private Collection<LazyLoadedObj<FileAttachment>> lazyLoadedFileAttachments;
	
	public Task(long id, String name) {
		this.id = id;
		this.name = name;
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LazyLoadedObj<Category> getLazyLoadedCategory() {
		return lazyLoadedCategory;
	}
	
	public void setLazyLoadedCategory(LazyLoadedObj<Category> lazyLoadedCategory) {
		this.lazyLoadedCategory = lazyLoadedCategory;
	}
	
	public LazyLoadedObj<Project> getLazyLoadedProject() {
		return lazyLoadedProject;
	}
	
	public void setLazyLoadedProject(LazyLoadedObj<Project> lazyLoadedProject) {
		this.lazyLoadedProject = lazyLoadedProject;
	}
	
	public LazyLoadedObj<FlowState> getLazyLoadedFlowState() {
		return lazyLoadedFlowState;
	}
	
	public void setLazyLoadedFlowState(LazyLoadedObj<FlowState> lazyLoadedFlowState) {
		this.lazyLoadedFlowState = lazyLoadedFlowState;
	}
	
	public Collection<LazyLoadedObj<User>> getLazyLoadedAssignedProgrammers() {
		return lazyLoadedAssignedProgrammers;
	}
	
	public void setLazyLoadedAssignedProgrammers(Collection<LazyLoadedObj<User>> lazyLoadedAssignedProgrammers) {
		this.lazyLoadedAssignedProgrammers = lazyLoadedAssignedProgrammers;
	}
	
	public Collection<LazyLoadedObj<User>> getLazyLoadedAssignedTesters() {
		return lazyLoadedAssignedTesters;
	}
	
	public void setLazyLoadedAssignedTesters(Collection<LazyLoadedObj<User>> lazyLoadedAssignedTesters) {
		this.lazyLoadedAssignedTesters = lazyLoadedAssignedTesters;
	}
	
	public Collection<LazyLoadedObj<User>> getLazyLoadedPeopleWantingUpdates() {
		return lazyLoadedPeopleWantingUpdates;
	}
	
	public void setLazyLoadedPeopleWantingUpdates(Collection<LazyLoadedObj<User>> lazyLoadedPeopleWantingUpdates) {
		this.lazyLoadedPeopleWantingUpdates = lazyLoadedPeopleWantingUpdates;
	}
	
	public LazyLoadedObj<User> getLazyLoadedTaskCreator() {
		return lazyLoadedTaskCreator;
	}
	
	public void setLazyLoadedTaskCreator(LazyLoadedObj<User> lazyLoadedTaskCreator) {
		this.lazyLoadedTaskCreator = lazyLoadedTaskCreator;
	}
	
	public LazyLoadedObj<Task> getParentTask() {
		return parentTask;
	}
	
	public void setParentTask(LazyLoadedObj<Task> parentTask) {
		this.parentTask = parentTask;
	}
	
	public Collection<LazyLoadedObj<Task>> getChildTasks() {
		return childTasks;
	}
	
	public void setChildTasks(Collection<LazyLoadedObj<Task>> childTasks) {
		this.childTasks = childTasks;
	}
	
	public Calendar getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}
	
	public Calendar getWorkStartedOn() {
		return workStartedOn;
	}
	
	public void setWorkStartedOn(Calendar workStartedOn) {
		this.workStartedOn = workStartedOn;
	}
	
	public Calendar getWorkFinishedOn() {
		return workFinishedOn;
	}
	
	public void setWorkFinishedOn(Calendar workFinishedOn) {
		this.workFinishedOn = workFinishedOn;
	}
	
	public LazyLoadedObj<Priority> getLazyLoadedPriority() {
		return lazyLoadedPriority;
	}
	
	public void setLazyLoadedPriority(LazyLoadedObj<Priority> lazyLoadedPriority) {
		this.lazyLoadedPriority = lazyLoadedPriority;
	}
	
	public Set<TaskTag> getTags() {
		return tags;
	}
	
	public void setTags(Set<TaskTag> tags) {
		this.tags = tags;
	}
	
	public Collection<LazyLoadedObj<TaskLog>> getLazyLoadedTaskLogs() {
		return lazyLoadedTaskLogs;
	}
	
	public void setLazyLoadedTaskLogs(Collection<LazyLoadedObj<TaskLog>> lazyLoadedTaskLogs) {
		this.lazyLoadedTaskLogs = lazyLoadedTaskLogs;
	}
	
	public Collection<LazyLoadedObj<FileAttachment>> getLazyLoadedFileAttachments() {
		return lazyLoadedFileAttachments;
	}
	
	public void setLazyLoadedFileAttachments(Collection<LazyLoadedObj<FileAttachment>> lazyLoadedFileAttachments) {
		this.lazyLoadedFileAttachments = lazyLoadedFileAttachments;
	}
	
}
