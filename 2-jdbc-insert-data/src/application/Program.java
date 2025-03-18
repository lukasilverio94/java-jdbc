package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {
	public static void main(String[] args) {

		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			conn = DB.getConnection();

			statement = conn.createStatement(); // instantiate Statement

			resultSet = statement.executeQuery("select * from department"); // write the sql query to execute and store at resultSet var
																			

			while (resultSet.next()) {
				System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("name"));
			}
		} 
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally { // manually close external resources that are not part of JVM
			DB.closeResultSet(resultSet);
			DB.closeStatement(statement);
			DB.closeConnection();
		}

	}
}
