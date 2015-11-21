package com.martincarney.bugTracker.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Shared parent of controller classes, containing utility and convenience methods useful
 * in most controllers.
 * @author Martin Carney
 */
public abstract class ControllerBase {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerBase.class);
	
	public static long getIdParameter(HttpServletRequest request, String parameterName) {
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
}
