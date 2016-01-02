package com.martincarney.bugTracker.controller.app;

/**
 * App-wide settings which vary by installation, but not by user session.
 * @author Martin Carney
 */
public class AppSettings {
	
	private AppSettings() throws InstantiationException { // prevents instantiation
		throw new InstantiationException();
	}
	
	public static boolean squelchMissingTranslationErrors = false;
	
}
