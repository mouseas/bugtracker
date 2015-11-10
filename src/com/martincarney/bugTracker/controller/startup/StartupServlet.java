package com.martincarney.bugTracker.controller.startup;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.martincarney.bugTracker.database.DBMaintenanceDAO;

/**
 * Does server initialization tasks including updating the database
 *
 * @author Martin
 */
public class StartupServlet extends HttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(StartupServlet.class);
	
	@Override
	public void init() {
		logger.info("================Running StartupServlet================");
		
		DBMaintenanceDAO dbMaintDAO = new DBMaintenanceDAO();
		dbMaintDAO.initializeDatabase();
		
		logger.info("=================StartupServlet Done==================");
	}
}
