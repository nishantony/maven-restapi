package com.qb.mavendemo.validator;

import com.qb.mavendemo.model.Student;
import com.qb.mavendemo.model.Validator;

public class StudentValidator {

	Validator v = new Validator();
	EmailValidator e = new EmailValidator();
	PasswordValidator p = new PasswordValidator();

	public Validator validation(Student s) {
		String email = s.getEmail();
		String pass = s.getPass();

		if (e.validateEmail(email))
			v.setEmailFlag(1);
		else
			v.setEmailFlag(0);

		if (p.validatePassword(pass))
			v.setPasswordFormat(1);
		else
			v.setPasswordFormat(0);

		if (p.checkLength(pass))
			v.setPasswordLength(1);
		else
			v.setPasswordLength(0);

		if (v.getEmailFlag() == 1 && v.getPasswordFormat() == 1 && v.getPasswordLength() == 1)
			v.setFlag(1);
		else
			v.setFlag(0);

		return v;
	}
}
