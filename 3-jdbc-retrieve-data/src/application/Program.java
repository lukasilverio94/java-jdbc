package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {
	public static void main(String[] args){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(name, email, birthdate, basesalary, departmentid)"
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)" // We don't add yet, thats why we use '?' representing each field(5 in this case)
					);
			// populating fields
			st.setString(1, "Karl Marx");
			st.setString(2, "comunist@karl.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 2);

			//returns a integer N in how many rows has been added (st.executeUpdate);
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id: " + id);
				}
			}
			else {
				System.out.println("No rows affected!");
			}
			
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(ParseException ex) {
			ex.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}
