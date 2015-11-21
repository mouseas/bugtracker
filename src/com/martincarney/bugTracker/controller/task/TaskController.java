package com.martincarney.bugTracker.controller.task;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.martincarney.bugTracker.controller.ControllerBase;
import com.martincarney.bugTracker.database.task.TaskDAO;
import com.martincarney.bugTracker.model.common.LazyLoadedObj;
import com.martincarney.bugTracker.model.task.Task;

/**
 * Controls various Task related actions.
 * @author Martin Carney
 */
@Controller
public class TaskController extends ControllerBase{
	
//	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@RequestMapping(value={"/task/view"}, method=RequestMethod.GET)
	public String viewTask(ModelMap model, HttpServletRequest request) {
		TaskDAO taskDAO = new TaskDAO();
		
		long taskId = getIdParameter(request, "id");
		
		Task task = taskDAO.getTask(taskId);
		
		model.put("task", task);
		
		return "view/task/viewTask";
	}
	
	@RequestMapping(value={"/task"}, method=RequestMethod.GET)
	public String tasks(ModelMap model, HttpServletRequest request) {
		TaskDAO taskDAO = new TaskDAO();
		
		List<LazyLoadedObj<Task>> taskList = taskDAO.getTaskList();
		
		model.put("taskList", taskList);
		
		return "view/task/taskHome";
	}
}
