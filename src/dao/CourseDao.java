package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.CourseBeans;

public class CourseDao {


	public static int addCourse(CourseBeans cb) throws SQLException
	{
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();	//statement to create query

		//Prepare the sql statement to execute
		/*********PREPARE SQL FOR INSERTING COURSE RECORDS*************/

		String sql = "INSERT INTO COURSE values(null,'"+cb.getC_name()+"','"+cb.getC_duration()+"','"+cb.getC_fee()+"')";
		int status = stmt.executeUpdate(sql);
		conn.close();
		return status;

	}

	public static ResultSet selectCourse() throws SQLException
	{
		 Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		/*********PREPARE SQL FOR GETTING ALL RECORDS*************/

		String sql = "SELECT cid as 'CID' , cname as 'Course Name', cduration as 'Course Duration', cfee as 'Course Fee (in NRs.)' FROM COURSE";


		ResultSet rs = stmt.executeQuery(sql);

		return rs;

//		while(rs.next())
//		{
//			System.out.println(rs.getInt(1));
//			System.out.println(rs.getString(2));
//			System.out.println(rs.getInt(3));
//			System.out.println(rs.getInt(4));
//		}
	}

	public static int deleteCourse(int id) throws SQLException
	{
		Connection con;

			con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM course WHERE cid='"+id+"'";
			int delStatus = stmt.executeUpdate(sql);
			con.close();

			return delStatus;

	}

	public static int updateCourse(CourseBeans cb, int id) throws SQLException
	{
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();

		String sql = "UPDATE course SET cname='"+cb.getC_name()+"', cduration='"+cb.getC_duration()+"', cfee='"+cb.getC_fee()+"' WHERE cid='"+id+"'";
		int updateStatus = stmt.executeUpdate(sql);
		return updateStatus;

	}

	public static CourseBeans getCourseById(int id) throws SQLException {
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();

		String sql = "SELECT * FROM course WHERE cid='"+id+"'";

		ResultSet rs = stmt.executeQuery(sql);

		CourseBeans cb = new CourseBeans();
		while(rs.next())
		{
			cb.setC_name(rs.getString(2));
			cb.setC_duration(rs.getInt(3));
			cb.setC_fee(rs.getInt(4));
		}
		return cb;


	}


}
