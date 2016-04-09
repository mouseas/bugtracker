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
	
	private Collection<User> assignedProgrammers;
	
	private Collection<User> assignedTesters;
	
	private Collection<User> peopleWantingUpdates;
	
	private User taskCreator;
	
	private Task parentTask;
	
	private Collection<Task> childTasks;
	
	private Calendar createdOn;
	
	private Calendar workStartedOn;
	
	private Calendar workFinishedOn;
	
	private Priority priority;
	
	private Set<TaskTag> tags;
	
	private Collection<TaskLog> taskLogs;
	
	private Collection<FileAttachment> fileAttachments;
	
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
	
	public Priority getPriority() {
		return priority;
	}
	
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	public Set<TaskTag> getTags() {
		return tags;
	}
	
	public void setTags(Set<TaskTag> tags) {
		this.tags = tags;
	}
	
	public Collection<User> getAssignedProgrammers() {
		return assignedProgrammers;
	}
	
	public void setAssignedProgrammers(Collection<User> assignedProgrammers) {
		this.assignedProgrammers = assignedProgrammers;
	}
	
	public Collection<User> getAssignedTesters() {
		return assignedTesters;
	}
	
	public void setAssignedTesters(Collection<User> assignedTesters) {
		this.assignedTesters = assignedTesters;
	}
	
	public Collection<User> getPeopleWantingUpdates() {
		return peopleWantingUpdates;
	}
	
	public void setPeopleWantingUpdates(Collection<User> peopleWantingUpdates) {
		this.peopleWantingUpdates = peopleWantingUpdates;
	}
	
	public User getTaskCreator() {
		return taskCreator;
	}
	
	public void setTaskCreator(User taskCreator) {
		this.taskCreator = taskCreator;
	}
	
	public Task getParentTask() {
		return parentTask;
	}
	
	public void setParentTask(Task parentTask) {
		this.parentTask = parentTask;
	}
	
	public Collection<Task> getChildTasks() {
		return childTasks;
	}
	
	public void setChildTasks(Collection<Task> childTasks) {
		this.childTasks = childTasks;
	}
	
	public Collection<TaskLog> getTaskLogs() {
		return taskLogs;
	}
	
	public void setTaskLogs(Collection<TaskLog> taskLogs) {
		this.taskLogs = taskLogs;
	}
	
	public Collection<FileAttachment> getFileAttachments() {
		return fileAttachments;
	}
	
	public void setFileAttachments(Collection<FileAttachment> fileAttachments) {
		this.fileAttachments = fileAttachments;
	}
}
