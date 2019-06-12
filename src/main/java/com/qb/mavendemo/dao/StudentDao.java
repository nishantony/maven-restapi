package com.qb.mavendemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.qb.mavendemo.model.Student;

public class StudentDao extends BaseDao{
	
	public List<Student> getRecords(int start, int total) {
		List<Student> list = new ArrayList<Student>();
		try {
			Connection con = getConnection();
			// Preparing SQL query
			PreparedStatement ps = con
					.prepareStatement("select * from Student_details limit " + (start - 1) + "," + total + ";");
			ResultSet rs = ps.executeQuery();
			// Retrieve values from ResultSet and set into Student object
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setDept(rs.getString(3));
				s.setEmail(rs.getString(4));
				s.setPass(rs.getString(5));
				// add object to list
				list.add(s);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public int setRecords(String name, String email, String pass, String dept) {

		int i = 0;
		try {
			Connection con = getConnection();
			// Preparing SQL query
			PreparedStatement pst = con.prepareStatement("insert into Student_details values(default,?,?,?,?);");
			pst.setString(1, name);
			pst.setString(2, dept);
			pst.setString(3, email);
			pst.setString(4, pass);

			i = pst.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}

	public int removeRecords(String email, String pass) {
		int i = 0;
		try {
			Connection con = getConnection();
			// Preparing SQL query
			PreparedStatement pst = con.prepareStatement("delete from Student_details where "
								+ "(email='" + email + "' and password='" + pass + "');");

			i = pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}

	public int updateRecords(String email, String pass, String dept) {
		String query;
		int i = 0;
		try {
			Connection con = getConnection();
			// Prepare query
			query = "update Student_details set department='" + dept 
					+ "' where (email='" + email + "' and password='"+ pass + "');";
			PreparedStatement pst = con.prepareStatement(query);
			i = pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}

	public int loginStudent(String email, String pass) {
		int i = 0;
		String query;
		query = "select * from Student_details " + "where email='" + email 
											+ "' && password='" + pass + "';";

		try {

			Connection con = getConnection();

			// Preparing SQL query
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery(query);

			if (rs.next())
				i = 1;
			con.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return i;
	}
}
