package com.bankapp.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	
	private static final String JDBC_MYSQL_HOST = "jdbc:mysql://localhost:3306/";
	private static final String DB_NAME = "mybank";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "5703";
	
	private DBUtils() {
	}
	
	public static Connection getConnection() throws Exception {
		try {
		//	Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(JDBC_MYSQL_HOST + DB_NAME, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}