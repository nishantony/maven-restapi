package com.qb.mavendemo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.qb.mavendemo.model.Student;
import com.qb.mavendemo.service.StudentService;
import com.qb.mavendemo.validator.EmailValidator;

/**
 * Servlet implementation class StudentLoginController
 */
@WebServlet("/StudentLogin")
public class StudentLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String email, pass;
	Connection con = null;
	ServletInputStream input = null;
	StudentService ss = new StudentService();
	Student s = new Student();
	EmailValidator e = new EmailValidator();
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentLoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		input = request.getInputStream();
		int i = 0, flag = 0;
		try {

			s = mapper.readValue(input, Student.class);
			email = s.getEmail();
			pass = s.getPass();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (e.validateEmail(email))
			flag = 1;
		else
			out.println("Invalid email");

		if (flag > 0) {
			i = ss.callLoginStudent(s);

			if (i > 0)
				out.println("Login Successfully");
			else
				out.println("Incorrect password");
		}
		out.close();
	}
}
