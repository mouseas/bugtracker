package com.martincarney.bugTracker.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.martincarney.bugTracker.controller.ControllerBase;
import com.martincarney.bugTracker.database.UserDAO;
import com.martincarney.bugTracker.database.task.TaskDAO;
import com.martincarney.bugTracker.model.task.Task;
import com.martincarney.bugTracker.model.user.User;

@Controller
public class UserController extends ControllerBase {
	
	@Override
	protected String view(String endPoint) {
		return super.view("user/" + endPoint);
	}
	
	@RequestMapping(value="/user/view", method=RequestMethod.GET)
	public String viewUser(ModelMap model, HttpServletRequest request) {
		UserDAO userDAO = new UserDAO();
		
		long userId = getNumericParameter(request, "id");
		
		User user = userDAO.getUserById(userId);
		
		if (user == null) {
			saveError(request.getSession(), "User not found.");
			redirect("/");
		}
		
		model.put("user", user);
		
		return view("viewUser");
	}
}
