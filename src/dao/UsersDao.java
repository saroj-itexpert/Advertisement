package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.LoginBeans;
import beans.UserBeans;

public class UsersDao {

	public static int addUser(UserBeans ub) throws SQLException
	{

		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();

		String sql = "INSERT INTO USERS values(null,'"+ub.getUsername()+"','"+ub.getContact()+"','"+ub.getPassword()+"','"+ub.getRe_password()+"')";
		int status = stmt.executeUpdate(sql);

		return status;

	}

//	public static ResultSet loginUser(LoginBeans lb) throws SQLException {
//
//
//		Connection conn = DBConnection.getConnection();
//		Statement stmt = conn.createStatement();
//
//		String sql = "SELECT * FROM USERS WHERE username='"+lb.getUsername()+"' AND password='"+lb.getPassword()+"'";
//		ResultSet rs = stmt.executeQuery(sql);
//		return rs;
//
//
//}

}
