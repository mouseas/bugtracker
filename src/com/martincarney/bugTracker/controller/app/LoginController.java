package com.martincarney.bugTracker.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.martincarney.bugTracker.controller.ControllerBase;
import com.martincarney.bugTracker.database.UserDAO;
import com.martincarney.bugTracker.form.LoginForm;
import com.martincarney.bugTracker.model.user.User;

@Controller
public class LoginController extends ControllerBase {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Override
	protected String view(String endPoint) {
		return super.view("login/" + endPoint);
	};
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String displayLogin(ModelMap model, HttpServletRequest request) {
		if (isLoggedIn(request)) {
			saveMessage(request.getSession(), "Already logged in.");
			return redirect("/");
		}
		
		LoginForm loginBean = new LoginForm();
		model.put("loginForm", loginBean);
		return view("login");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String executeLogin(@ModelAttribute("loginForm") LoginForm loginForm, ModelMap model, BindingResult errors, HttpServletRequest request) {
		if (isLoggedIn(request)) {
			saveMessage(request.getSession(), "Already logged in.");
			return redirect("/");
		}
		
		if (StringUtils.isEmpty(loginForm.getUsername())) {
			errors.reject("errors.login.noUsernameProvided");
		}
		
		if (errors.hasErrors()) {
			saveErrors(request, errors);
			return view("login");
		}
		
		UserDAO userDAO = new UserDAO();
		boolean isValidLogin = userDAO.isLoginValid(loginForm.getUsername(), loginForm.getPassword());
		
		if (isValidLogin) {
			logger.debug("User login \"" + loginForm.getUsername() + "\" was successful!");
			
			User currentUser = userDAO.getUserByUsername(loginForm.getUsername());
			
			request.getSession().setAttribute(AppConstants.SESSION_CURRENT_USER, currentUser);
		} else {
			errors.reject("errors.login.invalidCombo");
		}
		
		if (errors.hasErrors()) {
			saveErrors(request, errors);
			return view("login");
		} else {
			saveMessage(request.getSession(), "Successfully logged in.");
			return redirect("/");
		}
	}
	
	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String executeLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.removeAttribute(AppConstants.SESSION_CURRENT_USER);
		saveMessage(request.getSession(), "Successfully logged out.");
		
		return redirect("/login");
	}
	
}
