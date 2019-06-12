package com.qb.mavendemo.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;



abstract class BaseDao {
	
	public Connection getConnection() {
		Properties prop = new Properties();
		InputStream input = null;
		Connection con = null;
		try {
			// Establishing JDBC connectivity
			input = BaseDao.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(input);
			String url = prop.getProperty("DB_URL");
			String driver = prop.getProperty("DB_DRIVER_CLASS");
			String user = prop.getProperty("DB_USER");
			String password = prop.getProperty("DB_PASSWORD");
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	
	}
}
