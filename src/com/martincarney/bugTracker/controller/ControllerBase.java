package com.martincarney.bugTracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import com.martincarney.bugTracker.controller.app.AppConstants;
import com.martincarney.bugTracker.model.user.User;

/**
 * Shared parent of controller classes, containing utility and convenience methods useful
 * in most controllers.
 * @author Martin Carney
 */
public abstract class ControllerBase {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerBase.class);
	
	/**
	 * Gets a numeric parameter from the request. The parameter MUST be present, and MUST be parsable
	 * as a Long.
	 * @return
	 */
	// TODO make a more robust version that has options for whether it's required and whether to throw
	// an exception if it's invalid. Should be able to accept a BindingResult, too.
	public static long getNumericParameter(HttpServletRequest request, String parameterName) {
		if (request == null || parameterName == null) {
			throw new NullPointerException();
		}
		String paramValue = request.getParameter(parameterName);
		try {
			return Long.parseLong(paramValue);
		} catch (NullPointerException | NumberFormatException e) {
			logger.error("Error getting \"" + parameterName + "\" parameter from the request.", e);
			return -1;
		}
	}
	
	/**
	 * Overrideable method for returning standard view pages. Each Controller should override this method to specify the
	 * common path used by most of the actions in that controller.
	 * @param endPoint The particular page to display, without having to specify the path common to most views used in the
	 * current controller.
	 */
	protected String view(String endPoint) {
		return "view/" + endPoint;
	}
	
	/**
	 * Redirect the user to a new url after completing the current action. A common use case is redirecting to the view page
	 * after inserting or updating a record.
	 * @param redirectTo path to redirect to. "/", for example, will redirect to the home page.
	 */
	protected String redirect(String redirectTo) {
		return "redirect:" + redirectTo;
	}
	
	/**
	 * Does the same as {@link #redirect(String)}, but also shows the user a generic "Access Denied" error message.
	 * @param redirectTo path to redirect to. "/", for example, will redirect to the home page.
	 */
	protected String accessDenied(String redirectTo, HttpServletRequest request) {
		saveError(request.getSession(), "Access Denied");
		return redirect(redirectTo);
	}
	
	/**
	 * Adds a message to be displayed on the resulting page.
	 */
	protected void saveMessage(HttpServletRequest request, String plaintextInfoMessage) {
		List<Object> messagesList = getMessageOrErrorList(request, AppConstants.MESSAGES_KEY);
		messagesList.add(plaintextInfoMessage);
	}
	
	/**
	 * Adds a message to be displayed on the resulting page, even after a redirect.
	 */
	protected void saveMessage(HttpSession session, String plaintextInfoMessage) {
		List<Object> messagesList = getMessageOrErrorList(session, AppConstants.MESSAGES_KEY);
		messagesList.add(plaintextInfoMessage);
	}
	
	/**
	 * Adds an error to be displayed on the resulting page.
	 */
	protected void saveError(HttpServletRequest request, String plaintextErrorMessage) {
		List<Object> errorsList = getMessageOrErrorList(request, AppConstants.ERRORS_KEY);
		errorsList.add(plaintextErrorMessage);
	}
	
	/**
	 * Adds an error message to be displayed on the resulting page, even after a redirect.
	 */
	protected void saveError(HttpSession session, String plaintextErrorMessage) {
		List<Object> errorsList = getMessageOrErrorList(session, AppConstants.ERRORS_KEY);
		errorsList.add(plaintextErrorMessage);
	}
	
	/**
	 * Adds multiple errors messages to be displayed on the resulting page.
	 */
	protected void saveErrors(HttpServletRequest request, BindingResult errors) {
		List<Object> errorsList = getMessageOrErrorList(request, AppConstants.ERRORS_KEY);
		errorsList.addAll(errors.getAllErrors());
	}

	/**
	 * Adds multiple errors messages to be displayed on the resulting page, even after a redirect.
	 */
	protected void saveErrors(HttpSession session, BindingResult errors) {
		List<Object> errorsList = getMessageOrErrorList(session, AppConstants.ERRORS_KEY);
		errorsList.addAll(errors.getAllErrors());
	}
	
	/**
	 * Gets the info messages or error messages list from the request. If the list hasn't been created yet, a new one
	 * is created and assigned.
	 */
	protected List<Object> getMessageOrErrorList(HttpServletRequest request, String key) {
		if (request.getAttribute(key) == null) {
			request.setAttribute(key, new ArrayList<>());
		}
		
		return (List<Object>) request.getAttribute(key);
	}
	
	/**
	 * Gets the info messages or error messages list from the session. If the list hasn't been created yet, a new one
	 * is created and assigned.
	 */
	protected List<Object> getMessageOrErrorList(HttpSession session, String key) {
		if (session.getAttribute(key) == null) {
			session.setAttribute(key, new ArrayList<>());
		}
		
		return (List<Object>) session.getAttribute(key);
	}
	
	/**
	 * Does a basic check to see if the user has logged in. This does not check any permissions or roles, just for the
	 * presence or absence of an authentication.
	 * @return
	 */
	protected boolean isLoggedIn(HttpServletRequest request) {
		return request.getSession().getAttribute(AppConstants.SESSION_CURRENT_USER) != null;
	}
	
	protected User getCurrentUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(AppConstants.SESSION_CURRENT_USER);
	}
}
