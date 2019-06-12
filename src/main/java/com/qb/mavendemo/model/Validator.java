package com.qb.mavendemo.model;

public class Validator {
	public int email_flag = 0;
	public int password_format = 0;
	public int password_length = 0;
	public int flag;

	public int getEmailFlag() {
		return email_flag;
	}

	public void setEmailFlag(int email_flag) {
		this.email_flag = email_flag;
	}

	public int getPasswordFormat() {
		return password_format;
	}

	public void setPasswordFormat(int password_format) {
		this.password_format = password_format;
	}

	public int getPasswordLength() {
		return password_length;
	}

	public void setPasswordLength(int password_length) {
		this.password_length = password_length;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}