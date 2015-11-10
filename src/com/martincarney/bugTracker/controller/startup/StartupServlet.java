package com.martincarney.bugTracker.controller.startup;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Does server initialization tasks including updating the database
 *
 * @author Martin
 */
public class StartupServlet extends HttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(StartupServlet.class);
	
	@Override
	public void init() {
		logger.debug("================Running StartupServlet================");
		
		logger.debug("=================StartupServlet Done==================");
	}
}
