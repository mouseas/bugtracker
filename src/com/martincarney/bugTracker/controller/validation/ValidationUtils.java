package com.martincarney.bugTracker.controller.validation;

import org.springframework.validation.BindingResult;

public class ValidationUtils {
	
	/**
	 * Checks that a required input has been filled in.
	 * @param value The value directly from the form.
	 * @param inputName The name of the input which is being validated. This should already be translated
	 * to the user's language.
	 */
	public static void required(String value, String inputName, BindingResult errors) {
		if (value == null || "".equals(value)) {
			errors.reject("errors.required", new Object[] {inputName}, null);
		}
	}
}
