package com.qb.mavendemo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import com.qb.mavendemo.model.Validator;
import com.qb.mavendemo.service.StudentService;
import com.qb.mavendemo.validator.StudentValidator;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/Student")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String email, pass, name, dept;
	// Connection con=null;
	StudentService ss = new StudentService();
	Validator v = new Validator();
	Student s = new Student();
	ServletInputStream input = null;
	StudentValidator sv = new StudentValidator();
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// Reading values from Json
		input = request.getInputStream();
		try {
			s = mapper.readValue(input, Student.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		v = sv.validation(s);
		if (v.getFlag() == 1) {
			int i = ss.callUpdateRecords(s);
			if (i > 0)
				out.println("Updated Successfully");
			else
				out.println("Updated Unsuccessful");
		}

		if (v.getEmailFlag() != 1)
			out.println("Invalid email");

		out.close();
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		input = request.getInputStream();
		// Reading values from Json
		try {

			s = mapper.readValue(input, Student.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		v = sv.validation(s);
		if (v.getFlag() == 1) {
			int i = ss.callRemoveRecords(s);
			// Display status
			if (i > 0)
				out.println("Deleted Successfully");
			else
				out.println("Delete Unsuccessful");
		}
		if (v.getEmailFlag() != 1)
			out.println("Invalid email");

		out.close();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String spageid = request.getParameter("page");
		int pageid = Integer.parseInt(spageid);
		int total = 5;
		// setting limits
		if (pageid == 1) {
		} else {
			pageid = pageid - 1;
			pageid = pageid * total + 1;
		}

		// Fetching records
		List<Student> list = ss.callGetRecords(pageid, total);
		// Display records
		for (Student s : list) {
			out.println(+s.getId());
			out.println(s.getName());
			out.println(s.getDept());
			out.println(s.getEmail() + "\n");
		}

		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Reading values from Json
		PrintWriter out = response.getWriter();
		int i = 0;
		input = request.getInputStream();
		try {
			// Create an object of StudentData class
			s = mapper.readValue(input, Student.class);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		v = sv.validation(s);
		if (v.getFlag() == 1) {
			i = ss.callSetRecords(s);
			if (i > 0)
				out.println("Inserted Successfully");
			else
				out.println("Insert Unsuccessful");
		}

		if (v.getEmailFlag() != 1)
			out.println("Invalid email");

		if (v.getPasswordFormat() != 1) {

			if (v.getPasswordLength() != 1)
				out.println("Password should have min 8 chars");
			else
				out.println("Password should have uppercase, lowercase, numerals and special chars");
		}

		out.close();
	}

}
