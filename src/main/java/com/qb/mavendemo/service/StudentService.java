package com.qb.mavendemo.service;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qb.mavendemo.dao.StudentDao;
import com.qb.mavendemo.model.Student;

public class StudentService {

	ObjectMapper mapper = new ObjectMapper();
	String email, pass, name, dept;
	StudentDao student1 = new StudentDao();
	public int callSetRecords(Student s) {
		int i = 0;
		//System.out.println("koii");
		name = s.getName();
		email = s.getEmail();
		pass = s.getPass();
		dept = s.getDept();
		i = student1.setRecords(name, email, pass, dept);
		return i;
	}

	public List<Student> callGetRecords(int pageid, int total) {
		List<Student> list = student1.getRecords(pageid, total);
		return list;
	}

	public int callUpdateRecords(Student s) {
		int i = 0;
		email = s.getEmail();
		pass = s.getPass();
		dept = s.getDept();
		i = student1.updateRecords(email, pass, dept);
		return i;
	}

	public int callRemoveRecords(Student s) {
		int i = 0;
		email = s.getEmail();
		pass = s.getPass();
		i = student1.removeRecords(email, pass);
		return i;
	}

	public int callLoginStudent(Student s) {
		int i = 0;
		email = s.getEmail();
		pass = s.getPass();
		i = student1.loginStudent(email, pass);
		return i;
	}

}
