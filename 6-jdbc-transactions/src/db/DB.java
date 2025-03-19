package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException ex) {
				throw new DbException(ex.getMessage());
			}

		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				throw new DbException(ex.getMessage());
			}

		}
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException ex) {
			throw new DbException(ex.getMessage());
		}
	}
	
	// To avoid try/catch blocks, create methods to close objects 
	// Close Statament obj (we will use on finally block on Program.java)
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// Close ResultSet obj (we will use on finally block on Program.java)
		public static void closeResultSet(ResultSet rs) {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
		}
}
