package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

	public static Connection getConnection() throws SQLException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_student","root","");
			System.out.println("connection success!");
			return conn;

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return null;
		}

	}


}
