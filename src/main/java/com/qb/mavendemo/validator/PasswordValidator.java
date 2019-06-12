package com.qb.mavendemo.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	private static final String PASS_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,30}$";

	private static Pattern pattern;

	private Matcher matcher;

	public PasswordValidator() {
		pattern = Pattern.compile(PASS_REGEX);
	}

	public boolean validatePassword(String pass) {
		matcher = pattern.matcher(pass);
		return matcher.matches();
	}

	public boolean checkLength(String pass) {
		if (pass.length() >= 8)
			return true;
		else
			return false;
	}

}
