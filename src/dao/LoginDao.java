package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.LoginBeans;

public class LoginDao {

	public static ResultSet loginUser() throws SQLException {


			Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "SELECT * FROM USERS ";//WHERE username='"+lb.getUsername()+"' AND password='"+lb.getPassword()+"'";
			ResultSet rs = stmt.executeQuery(sql);
			return rs;


	}

}
