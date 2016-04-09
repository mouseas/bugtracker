package com.martincarney.bugTracker.controller.task;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.martincarney.bugTracker.controller.ControllerBase;
import com.martincarney.bugTracker.controller.validation.ValidationUtils;
import com.martincarney.bugTracker.database.task.TaskDAO;
import com.martincarney.bugTracker.form.task.TaskForm;
import com.martincarney.bugTracker.model.common.LazyLoadedObj;
import com.martincarney.bugTracker.model.common.SearchResultSet;
import com.martincarney.bugTracker.model.task.Task;

/**
 * Controls various Task related actions.
 * @author Martin Carney
 */
@Controller
public class TaskController extends ControllerBase{
	
//	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Override
	protected String view(String endPoint) {
		return super.view("task/" + endPoint);
	}
	
	@RequestMapping(value="/task/view", method=RequestMethod.GET)
	public String viewTask(ModelMap model, HttpServletRequest request) {
		TaskDAO taskDAO = new TaskDAO();
		
		long taskId = getNumericParameter(request, "id");
		
		Task task = taskDAO.getTask(taskId);
		
		if (task == null) {
			saveError(request.getSession(), "Task not found.");
			redirect("/task");
		}
		
		model.put("task", task);
		
		return view("viewTask");
	}
	
	@RequestMapping(value="/task/**")
	public String tasks404(ModelMap model, HttpServletRequest request) {
		saveError(request.getSession(), "Page not found.");
		return redirect("/task");
	}
	
	@RequestMapping(value="/task", method=RequestMethod.GET)
	public String tasksList(ModelMap model, HttpServletRequest request) {
		TaskDAO taskDAO = new TaskDAO();
		
		SearchResultSet taskList = taskDAO.getTaskList();
		
		model.put("taskList", taskList);
		
		return view("taskHome");
	}
	
	@RequestMapping(value="/task/create", method=RequestMethod.GET)
	public String inputNewTask(ModelMap model, HttpServletRequest request) {
		if (!isLoggedIn(request)) {
			return accessDenied("/task", request);
		}
		
		model.put("taskForm", new TaskForm());
		
		return view("inputTask");
	}
	
	@RequestMapping(value="/task/insert", method=RequestMethod.POST)
	public String insertNewTask(@ModelAttribute TaskForm form, ModelMap model, HttpServletRequest request, BindingResult errors) {
		if (!isLoggedIn(request)) {
			return accessDenied("/task", request);
		}
		
		TaskDAO taskDAO = new TaskDAO();
		
		// validate input
		ValidationUtils.required(form.getName(), "Name", errors);
		ValidationUtils.required(form.getDescription(), "Description", errors);
		
		if (errors.hasErrors()) {
			saveErrors(request, errors);
			return view("inputTask");
		}
		
		// insert if validation passes
		Task newTask = new Task(0, form.getName());
		newTask.setDescription(form.getDescription());
		
		taskDAO.insertTask(newTask, getCurrentUser(request));
		
		return redirect("/task/view?id=" + newTask.getId());
	}
	
	@RequestMapping(value="/task/edit", method=RequestMethod.GET)
	public String inputEditTask(ModelMap model, HttpServletRequest request) {
		if (!isLoggedIn(request)) {
			return accessDenied("/task", request);
		}
		
		TaskForm form = new TaskForm();
		TaskDAO taskDAO = new TaskDAO();
		
		long taskId = getNumericParameter(request, "id");
		
		Task task = taskDAO.getTask(taskId);
		
		if (task == null) {
			saveError(request.getSession(), "The specified task does not exist.");
			return redirect("/task");
		}
		
		form.setId(task.getId());
		form.setName(task.getName());
		form.setDescription(task.getDescription());
		
		model.put("taskForm", form);
		
		return view("inputTask");
	}
	
	@RequestMapping(value="/task/update", method=RequestMethod.POST)
	public String updateTask(@ModelAttribute TaskForm form, ModelMap model, HttpServletRequest request, BindingResult errors) {
		if (!isLoggedIn(request)) {
			return accessDenied("/task", request);
		}
		
		TaskDAO taskDAO = new TaskDAO();
		
		// validate input
		ValidationUtils.required(form.getName(), "Name", errors);
		ValidationUtils.required(form.getDescription(), "Description", errors);
		
		if (errors.hasErrors()) {
			saveErrors(request, errors);
			return view("inputTask");
		}
		
		// insert if validation passes
		Task newTask = new Task(form.getId(), form.getName());
		newTask.setDescription(form.getDescription());
		
		taskDAO.updateTask(newTask, getCurrentUser(request));
		
		return redirect("/task/view?id=" + newTask.getId());
	}
}
